package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.SituacaoRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
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
    private ItemBuilder itemBuilder;

    @Autowired
    private SituacaoRepositorio situacaoRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private OfertaRepositorio ofertaRepositorio;



    @Override
    public Oferta construirEntidade() {
        Oferta oferta = new Oferta();
        List <Item> itens = new ArrayList<>();

        Usuario usuarioTroca = criarUsuarioTroca();
        Item itemTroca = criarItemTroca(usuarioTroca);

        itens.add(itemTroca);

        Usuario usuarioAlvo = criarUsuarioAlvo();
        Item itemAlvo = criarItemAlvo(usuarioAlvo);

        oferta.setUsuario(usuarioTroca);
        oferta.setItem(itemAlvo);
        oferta.setSituacao(situacaoRepositorio.getOne(SituacaoEnum.APROVADA.getId()));
        oferta.setItensOfertados(itens);

        return oferta;
    }

    private Usuario criarUsuarioTroca(){

        Usuario usuario = usuarioBuilder.criarOutroUsuario();
        usuario.setEmail("usuarioTROCA1@gmail.com");
        usuario.setCpf("58043162069");
        usuario = usuarioBuilder.persistir(usuario);

       return usuario;
    }

    private Item criarItemTroca(Usuario usuario){
        Item item = itemBuilder.criarNovoItem();
        item.setNome("Item TROCA");
        item.setDisponibilidade(true);
        item.setDescricao("Item teste para troca");
        item.setUsuario(usuario);
        item = itemBuilder.persistir(item);

        return item;
    }

    private Usuario criarUsuarioAlvo(){

        Usuario usuario = usuarioBuilder.criarOutroUsuario();
        usuario.setCpf("32492800032");
        usuario.setEmail("usuarioALVO@gmail.com");
        usuario.setNome("Usuario Alvo");
        usuario = usuarioBuilder.persistir(usuario);

        return usuario;
    }

    private Item criarItemAlvo(Usuario usuario){
        Item item = itemBuilder.criarNovoItem();
        item.setNome("Item ALVO");
        item.setDisponibilidade(true);
        item.setDescricao("Item teste ALVO");
        item.setUsuario(usuario);
        item = itemBuilder.persistir(item);

        return item;
    }


    @Override
    public Oferta persistir(Oferta entidade) {
        return ofertaMapper.toEntity(ofertaServico.salvar
                (ofertaMapper.toDto(entidade), ""));
//        return ofertaRepositorio.saveAndFlush(entidade);
    }
}
