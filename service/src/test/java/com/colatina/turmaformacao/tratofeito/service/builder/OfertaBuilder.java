package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class OfertaBuilder extends ConstrutorEntidade<Oferta>{

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    private OfertaServico ofertaServico;

    @Autowired
    private Usuario usuario;

    @Override
    public Oferta construirEntidade() {
        Oferta oferta = new Oferta();
        oferta.setUsuario();
        oferta.setItem();
        oferta.setSituacao();

        return oferta;
    }

    @Override
    public Oferta persistir(Oferta entidade) {
        return ofertaMapper.toEntity(ofertaServico.salvar(ofertaMapper.toDto(entidade)));
    }
}
