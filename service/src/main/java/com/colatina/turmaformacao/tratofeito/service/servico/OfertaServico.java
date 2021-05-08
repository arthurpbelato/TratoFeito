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
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
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
    private final ItemMapper itemMapper;

    private Oferta getOferta(Long id){
        return ofertaRepositorio.findOfertaById(id);
    }

    public List<OfertaListagemDTO> listar(){
        return ofertaRepositorio.listOferta();
    }

    public OfertaDTO obterPorId(Long id){
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
        UsuarioDTO usuarioAlvo = usuarioServico.obterPorId(itemAlvo.getIdUsuario());

        ofertaRepositorio.save(oferta);
        emailServico.sendMail(criarEmailNovaOferta(usuarioAlvo, itemAlvo));
        return ofertaMapper.toDto(oferta);
    }

    public void excluir(Long id){
        ofertaRepositorio.deleteById(id);
    }


    public void aceitar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.APROVADA.getId()));

        UsuarioDTO usuarioDTO = usuarioServico.obterPorId(oferta.getUsuario().getId());
        Usuario ofertante = new Usuario(usuarioDTO.getId());
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
        ItemDTO itemDTO = itemMapper.toDto(itemAlvo);
        itemServico.alterar(itemDTO);
        itemServico.salvarLista(itensOfertados);
        ofertaRepositorio.save(oferta);
        emailServico.sendMail(criarEmailAceitarOferta(usuarioDTO, itemDTO));
        if(!ofertasItemTrocado.isEmpty()){
            ofertasItemTrocado.forEach(o ->
                    o.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.CANCELADA.getId())));
            ofertaRepositorio.saveAll(ofertasItemTrocado);
        }

    }

    public void recusar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        UsuarioDTO usuarioDTO = usuarioServico.obterPorId(oferta.getUsuario().getId());
        ItemDTO itemDTO = itemServico.obterPorId(oferta.getItem().getId());

        emailServico.sendMail(criarEmailRecusarOferta(usuarioDTO,itemDTO));
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.RECUSADA.getId()));
    }

    private EmailDTO criarEmailRecusarOferta(UsuarioDTO usuarioDTO, ItemDTO itemDTO){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Sua oferta foi recusada...");
        email.setCorpo("A oferta que você fez no item "+itemDTO.getNome()+" foi recusada!<br>" +
                "Não desanime! Você pode realizar ofertas diferentes para tentar conseguir esse item!");
        email.setDestinatario(usuarioDTO.getEmail());
        return email;
    }

    private EmailDTO criarEmailAceitarOferta(UsuarioDTO usuarioDTO, ItemDTO itemDTO){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Sua oferta foi aceita!!!");
        email.setCorpo("Boas notícias! A oferta que você no item: "+itemDTO.getNome() + " foi aceita!<br>" +
                "Seu novo item te aguarda!");
        email.setDestinatario(usuarioDTO.getEmail());
        return email;
    }

    private EmailDTO criarEmailNovaOferta(UsuarioDTO usuarioDTO, ItemDTO itemDTO){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Chegou uma oferta!!");
        email.setCorpo("Alguém fez uma oferta no seu item: "+itemDTO.getNome());
        email.setDestinatario(usuarioDTO.getEmail());
        return email;
    }

}
