package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.repositorio.CategoriaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.ItemServico;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemBuilder extends ConstrutorEntidade<Item> {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemServico itemServico;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Override
    public Item construirEntidade() {
        Item entidade = new Item();
        entidade.setNome("ItemTeste");
        entidade.setDescricao("Iteminseridoparateste");
        entidade.setDisponibilidade(true);
        entidade.setSituacao("OK");
        entidade.setFoto(new byte[50]);
        entidade.setUsuario(usuarioBuilder.construir());
        entidade.setCategoria(categoriaRepositorio.findById(1L).orElse(null));
        return entidade;

    }

    public Item criarNovoItem() {
        Item entidade = new Item();
        entidade.setNome("item-teste-alterado");
        entidade.setDescricao("Item inserido e alterado para teste.");
        entidade.setFoto(new byte[20]);
        entidade.setDisponibilidade(true);
        entidade.setSituacao("Item inserido e alterado para teste.");
        entidade.setCategoria(categoriaRepositorio.findById(5L).orElse(null));
        return entidade;

    }

    @Override
    public Item persistir(Item entidade) {
        return itemMapper.toEntity(itemServico.salvar(itemMapper.toDto(entidade)));
    }

}
