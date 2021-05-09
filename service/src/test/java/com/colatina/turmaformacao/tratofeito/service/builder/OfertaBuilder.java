package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Situacao;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
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

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ItemBuilder itemBuilder;


    @Override
    public Oferta construirEntidade() {
        Oferta oferta = new Oferta();
        Usuario usuario = usuarioBuilder.construir();
        List <Item> itens = new ArrayList<>();

        itens.add(itemBuilder.customizar(i -> {
            i.setNome("Item2");
            i.setDescricao("Item que esta sendo ofertado para o item alvo");
            i.setUsuario(usuarioBuilder.customizar( u -> {
                u.setEmail("usuariotroca123@gmail.com");
                u.setCpf("58043162069");
            }).construir());
        }).construir());

        oferta.setItem(itemBuilder.construir());
        oferta.setUsuario(usuario);
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.AGUARDANDO_APROVACAO.getId()));
        oferta.setItensOfertados(itens);

        return oferta;
    }



    @Override
    public Oferta persistir(Oferta entidade) {
        return ofertaMapper.toEntity(ofertaServico.salvar(ofertaMapper.toDto(entidade)));
    }
}
