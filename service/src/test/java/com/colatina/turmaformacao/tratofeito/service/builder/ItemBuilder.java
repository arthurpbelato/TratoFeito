package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.repositorio.CategoriaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.ItemServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class ItemBuilder extends ConstrutorEntidade<Item> {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemServico itemServico;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public Item construirEntidade() {
        Item entidade = new Item();
        entidade.setNome("item-teste");
        entidade.setDescricao("Item inserido para teste.");
        entidade.setDisponibilidade(true);
        entidade.setSituacao("OK");
        entidade.setFoto(new byte[50]);
        entidade.setUsuario(usuarioRepositorio.findById(28L).orElse(null));
        entidade.setCategoria(categoriaRepositorio.findById(1L).orElse(null));
        return entidade;
    }

    @Override
    public Item persistir(Item entidade) {
        ItemDTO entidadeSalva = itemServico.salvar(itemMapper.toDto(entidade));
        return itemMapper.toEntity(entidadeSalva);
    }

}
