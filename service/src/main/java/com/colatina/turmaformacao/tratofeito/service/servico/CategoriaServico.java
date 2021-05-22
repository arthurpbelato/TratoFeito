package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Categoria;
import com.colatina.turmaformacao.tratofeito.service.repositorio.CategoriaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.SelectItemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.CategoriaSelectItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategoriaServico {

    private final CategoriaSelectItemMapper categoriaMapper;
    private final CategoriaRepositorio categoriaRepositorio;

    public List<SelectItemDTO> listar() {
        List<Categoria> categorias = categoriaRepositorio.findAll();
        return categoriaMapper.toDto(categorias);
    }

}
