package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.repositorio.ItemRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemListagemMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServico {

    private final ItemRepositorio itemRepositorio;
    private final ItemMapper itemMapper;
    private final ItemListagemMapper itemListagemMapper;

    private Item getItem(Long id){
        return itemRepositorio.findById(id).orElseThrow(() ->
                new RegraNegocioException("Item nao encontrado"));
    }

    public List<ItemListagemDTO> listar() {
        List<Item> itens = itemRepositorio.findAll();
        return itemListagemMapper.toDto(itens);
    }

    public ItemDTO obterPorId(Long id) {
        Item item = getItem(id);
        return itemMapper.toDto(item);
    }

    public ItemDTO salvar(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        itemRepositorio.save(item);
        return itemMapper.toDto(item);
    }

    public ItemDTO alterar(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        itemRepositorio.save(item);
        return itemMapper.toDto(item);
    }

    public void deletar(Long id) {
        itemRepositorio.deleteById(id);
    }

    public ItemDTO disponibilizar(ItemDTO itemDTO, boolean disponibilidade) {
        Item item = itemMapper.toEntity(itemDTO);
        item.setDisponibilidade(disponibilidade);
        itemRepositorio.save(item);
        return itemDTO;
    }

}
