package com.colatina.turmaformacao.tratofeito.service.servico;


import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Situacao;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
import com.colatina.turmaformacao.tratofeito.service.repositorio.ItemRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OfertaServico {

    private final OfertaRepositorio ofertaRepositorio;
    private final OfertaMapper ofertaMapper;

    private final ItemRepositorio itemRepositorio;

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
        oferta.setSituacao(Situacao.getAguardandoAprovacao());
        ofertaRepositorio.save(oferta);
        return ofertaMapper.toDto(oferta);
    }

    public void excluir(long id){
        ofertaRepositorio.deleteById(id);
    }


    public void aceitar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
        oferta.setSituacao(Situacao.getAprovada());

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
        itensOfertados.forEach(item -> item.setUsuario(alvo));
        itemRepositorio.save(itemAlvo);
        itemRepositorio.saveAll(itensOfertados);
        ofertaRepositorio.save(oferta);

        if(!ofertasItemTrocado.isEmpty()){
            ofertasItemTrocado.forEach(o -> o.setSituacao(Situacao.getCancelada()));
            ofertaRepositorio.saveAll(ofertasItemTrocado);
        }

    }

    public void recusar(Long id) {
        Oferta oferta = ofertaRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Oferta não encontrada"));
    }
}
