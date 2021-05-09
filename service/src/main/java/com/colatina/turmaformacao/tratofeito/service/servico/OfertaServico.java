package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.EmailDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.EmailItemOfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
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
    private final SituacaoServico situacaoServico;
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
        itemIndisponivel(ofertaDTO);
        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        oferta.setSituacao(situacaoServico.getSituacao(SituacaoEnum.AGUARDANDO_APROVACAO.getId()));
        ofertaRepositorio.save(oferta);
        EmailItemOfertaDTO ofertaEmail = ofertaRepositorio.findItemEmailAlvo(oferta.getId());
        emailServico.sendMail(criarEmailNovaOferta(ofertaEmail));
        return ofertaMapper.toDto(oferta);
    }

    private void itemIndisponivel(OfertaDTO ofertaDTO){
        ItemDTO itemDTO = itemServico.obterPorId(ofertaDTO.getIdItemAlvo());
        List<Long> ids = ofertaDTO.getIdItensOfertados();
        if(!itemDTO.getDisponibilidade())
            throw new RegraNegocioException("Item alvo da oferta indisponível");
        for (Long id : ids) {
            itemDTO = itemServico.obterPorId(id);
            if (!itemDTO.getDisponibilidade())
                throw new RegraNegocioException("Ao menos um dos itens ofertados está indisponível");
        }
    }

    public void excluir(Long id){
        ofertaRepositorio.deleteById(id);
    }

    public void aceitar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        oferta.setSituacao(situacaoServico.getSituacao(SituacaoEnum.APROVADA.getId()));

        atualizarItensOfertados(oferta);
        atualizarItemAlvo(oferta);
        ofertaRepositorio.save(oferta);

        EmailItemOfertaDTO ofertaEmail = ofertaRepositorio.findItemEmailOfertante(id);
        emailServico.sendMail(criarEmailAceitarOferta(ofertaEmail));

        cancelarOfertasParalelas(oferta);
    }

    private void atualizarItemAlvo(Oferta oferta){
        Item itemAlvo = oferta.getItem();

        itemAlvo.setUsuario(new Usuario(oferta.getUsuario().getId()));
        itemAlvo.setDisponibilidade(false);

        itemServico.alterar(itemMapper.toDto(itemAlvo));
    }

    private void atualizarItensOfertados(Oferta oferta){
        List<Item> itensOfertados = oferta.getItensOfertados();
        Usuario alvo = new Usuario(oferta.getItem().getUsuario().getId());
        itensOfertados.forEach(item -> {
            item.setUsuario(alvo);
            item.setDisponibilidade(false);
        });
        itemServico.salvarLista(itensOfertados);
    }

    private void cancelarOfertasParalelas(Oferta oferta){
        List<Long> idsItems = oferta.getItensOfertados().stream().map(Item::getId).collect(Collectors.toList());

        List<Oferta> ofertasItemTrocado = ofertaRepositorio
                .obterOfertasComItemAlvoTrocado(oferta.getItem().getId(),
                        oferta.getId(),
                        SituacaoEnum.AGUARDANDO_APROVACAO.getId(),
                        idsItems);

        if(!ofertasItemTrocado.isEmpty()){
            ofertasItemTrocado.forEach(o ->
                    o.setSituacao(situacaoServico.getSituacao(SituacaoEnum.CANCELADA.getId())));
            ofertaRepositorio.saveAll(ofertasItemTrocado);
        }
    }

    public void recusar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        EmailItemOfertaDTO ofertaEmail = ofertaRepositorio.findItemEmailOfertante(id);
        emailServico.sendMail(criarEmailRecusarOferta(ofertaEmail));
        oferta.setSituacao(situacaoServico.getSituacao(SituacaoEnum.RECUSADA.getId()));
    }

    private EmailDTO criarEmailRecusarOferta(EmailItemOfertaDTO dto){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Sua oferta foi recusada...");
        email.setCorpo("A oferta que você fez no item "+dto.getNomeItem()+" foi recusada!<br>" +
                "Não desanime! Você pode realizar ofertas diferentes para tentar conseguir esse item!");
        email.setDestinatario(dto.getEmailUsuario());
        return email;
    }

    private EmailDTO criarEmailAceitarOferta(EmailItemOfertaDTO dto){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Sua oferta foi aceita!!!");
        email.setCorpo("Boas notícias! A oferta que você no item: "+dto.getNomeItem() + " foi aceita!<br>" +
                "Seu novo item te aguarda!");
        email.setDestinatario(dto.getEmailUsuario());
        return email;
    }

    private EmailDTO criarEmailNovaOferta(EmailItemOfertaDTO dto){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Chegou uma oferta!!");
        email.setCorpo("Alguém fez uma oferta no seu item: "+dto.getNomeItem());
        email.setDestinatario(dto.getEmailUsuario());
        return email;
    }

}
