package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.dto.ItemListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.repositorio.ItemRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemListagemMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemServico {

    private final ItemRepositorio itemRepositorio;
    private final ItemMapper itemMapper;
    private final ItemListagemMapper itemListagemMapper;

    private Item getItem(Long id){
        Item item = itemRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Item nao encontrado"));
        return item;
    }

    public List<ItemListagemDTO> listar() {
        List<ItemListagemDTO> itensDTO = new ArrayList<>();
        List<Item> itens = itemRepositorio.findAll();
        itensDTO = itemListagemMapper.toDto(itens);
        return itensDTO;
    }

    public ItemDTO obterPorId(Long id) {
        ItemDTO itemDTO = new ItemDTO();
        Item item = getItem(id);
        itemDTO = itemMapper.toDto(item);
        return itemDTO;
    }

    public ItemDTO salvar(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        itemRepositorio.save(item);
        return itemDTO;
    }

    public ItemDTO alterar(ItemDTO itemDTO) {
        Item item = itemMapper.toEntity(itemDTO);
        itemRepositorio.save(item);
        return itemDTO;
    }

    public void deletar(Long id) {
        itemRepositorio.deleteById(id);
    }
}
