package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
import com.colatina.turmaformacao.tratofeito.service.repositorio.SituacaoRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.ItemServico;
import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.UsuarioServico;
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
        List <Item> itens = new ArrayList<>();

        Usuario usuarioTroca = usuarioBuilder.criarOutroUsuario();
        usuarioTroca.setEmail("usuarioTROCA1@gmail.com");
        usuarioTroca.setCpf("58043162069");
        usuarioTroca = usuarioBuilder.persistir(usuarioTroca);
        Item itemTroca = itemBuilder.criarNovoItem();
        itemTroca.setNome("Item TROCA");
        itemTroca.setUsuario(usuarioTroca);
        itemTroca = itemBuilder.persistir(itemTroca);
        itens.add(itemTroca);

        itemBuilder.removerCustomizacao();
        usuarioBuilder.removerCustomizacao();

        Usuario usuarioAlvo = usuarioBuilder.criarOutroUsuario();
        usuarioAlvo.setCpf("32492800032");
        usuarioAlvo.setEmail("usuarioALVO@gmail.com");
        usuarioAlvo.setNome("Usuario Alvo");
        usuarioAlvo = usuarioBuilder.persistir(usuarioAlvo);
        Item itemAlvo = itemBuilder.criarNovoItem();
        itemAlvo.setNome("Item ALVO");
        itemAlvo.setUsuario(usuarioAlvo);
        itemAlvo = itemBuilder.persistir(itemAlvo);
        oferta.setUsuario(usuarioAlvo);
        oferta.setItem(itemAlvo);
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.APROVADA.getId()));
        oferta.setItensOfertados(itens);

        return oferta;
    }

    @Override
    public Oferta persistir(Oferta entidade) {
        return ofertaMapper.toEntity(ofertaServico.salvar(ofertaMapper.toDto(entidade)));
    }
}
