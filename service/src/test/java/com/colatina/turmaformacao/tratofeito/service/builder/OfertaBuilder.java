package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Situacao;
import com.colatina.turmaformacao.tratofeito.service.repositorio.SituacaoRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.ItemServico;
import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.UsuarioServico;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OfertaBuilder extends ConstrutorEntidade<Oferta>{

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    private OfertaServico ofertaServico;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioServico usuarioServico;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemServico itemServico;

    @Autowired
    private SituacaoRepositorio situacaoRepositorio;


    @Override
    public Oferta construirEntidade() {
        Oferta oferta = new Oferta();
//        Item item = new Item();
//        Situacao situacao = new Situacao();

        oferta.setUsuario(usuarioMapper.toEntity(usuarioServico.obterPorId(5000L)));
        oferta.setItem(itemMapper.toEntity(itemServico.obterPorId(5000L)));
        oferta.setSituacao(situacaoRepositorio.findById(1000L)
                .orElseThrow(()-> new RegraNegocioException("Situacao nao encontrada")));

//        oferta.setUsuario(usuarioBuilder.customizar(u -> {
//            u.setCpf("987654321");
//            u.setEmail("jorgin123@gmail.com");
//            }).construir());
//        oferta.setItem(item);
//        oferta.setSituacao(situacao);

        return oferta;
    }

    @Override
    public Oferta persistir(Oferta entidade) {
        return ofertaMapper.toEntity(ofertaServico.salvar(ofertaMapper.toDto(entidade)));
    }
}
