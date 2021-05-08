package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
import com.colatina.turmaformacao.tratofeito.service.repositorio.ItemRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.SituacaoRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.EmailDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OfertaServico {

    private final OfertaRepositorio ofertaRepositorio;
    private final OfertaMapper ofertaMapper;
    private final EmailServico emailServico;
    private final ItemServico itemServico;
    private final UsuarioServico usuarioServico;
    private final ItemRepositorio itemRepositorio;
    private final SituacaoRepositorio situacaoRepositorio;

    private Oferta getOferta(long id){
        return ofertaRepositorio.findOfertaById(id);
    }

    public List<OfertaListagemDTO> listar(){
        return ofertaRepositorio.listOferta();
    }

    public OfertaDTO obterPorId(long id){
        Oferta oferta = getOferta(id);
        return ofertaMapper.toDto(oferta);
    }

    public OfertaDTO atualizar(OfertaDTO ofertaDTO){
        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        ofertaRepositorio.save(oferta);
        return ofertaMapper.toDto(oferta);
    }

    public OfertaDTO salvar(OfertaDTO ofertaDTO){
        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.AGUARDANDO_APROVACAO.getId()));

        ItemDTO itemAlvo = itemServico.obterPorId(ofertaDTO.getIdItemAlvo());
        UsuarioDTO usuarioAlvo = usuarioServico.obterPorId(ofertaDTO.getIdUsuario());

        ofertaRepositorio.save(oferta);
        emailServico.sendMail(criarEmail(usuarioAlvo, itemAlvo));
        return ofertaMapper.toDto(oferta);
    }

    public void excluir(long id){
        ofertaRepositorio.deleteById(id);
    }


    public void aceitar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.APROVADA.getId()));

        Usuario ofertante = new Usuario(oferta.getUsuario().getId());
        Usuario alvo = new Usuario(oferta.getItem().getUsuario().getId());
        List<Item> itensOfertados = oferta.getItensOfertados();
        Item itemAlvo = oferta.getItem();

        List<Item> items = oferta.getItensOfertados();
        List<Long> idsItems = items.stream().map(Item::getId).collect(Collectors.toList());
        List<Oferta> ofertasItemTrocado = ofertaRepositorio
                .obterOfertasComItemAlvoTrocado(oferta.getItem().getId(),
                        id,
                        SituacaoEnum.AGUARDANDO_APROVACAO.getId(),
                        idsItems);

        itemAlvo.setUsuario(ofertante);
        itemAlvo.setDisponibilidade(false);
        itensOfertados.forEach(item -> {
            item.setUsuario(alvo);
            item.setDisponibilidade(false);
        });
        itemRepositorio.save(itemAlvo);
        itemRepositorio.saveAll(itensOfertados);
        ofertaRepositorio.save(oferta);

        if(!ofertasItemTrocado.isEmpty()){
            ofertasItemTrocado.forEach(o ->
                    o.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.CANCELADA.getId())));
            ofertaRepositorio.saveAll(ofertasItemTrocado);
        }

    }

    public void recusar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));

        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.RECUSADA.getId()));
    }

    private EmailDTO criarEmail(UsuarioDTO usuarioDTO, ItemDTO itemDTO){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Chegou uma oferta!!");
        email.setCorpo("Alguém fez uma oferta no seu item: "+itemDTO.getNome());
        email.setDestinatario(usuarioDTO.getEmail());
        return email;
    }

}
