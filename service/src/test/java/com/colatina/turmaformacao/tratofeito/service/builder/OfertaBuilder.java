package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Situacao;
import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OfertaBuilder extends ConstrutorEntidade<Oferta>{

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    private OfertaServico ofertaServico;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Override
    public Oferta construirEntidade() {
        Oferta oferta = new Oferta();
        Item item = new Item();
        Situacao situacao = new Situacao();

        oferta.setUsuario(usuarioBuilder.customizar(u -> {
            u.setCpf("987654321");
            u.setEmail("jorgin123@gmail.com");
            }).construir());
        oferta.setItem(item);
        oferta.setSituacao(situacao);

        return oferta;
    }

    @Override
    public Oferta persistir(Oferta entidade) {
        return ofertaMapper.toEntity(ofertaServico.salvar(ofertaMapper.toDto(entidade)));
    }
}
