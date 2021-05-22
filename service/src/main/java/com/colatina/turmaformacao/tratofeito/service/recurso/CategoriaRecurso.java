package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.servico.CategoriaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.SelectItemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categorias")
public class CategoriaRecurso {
    private final CategoriaServico categoriaServico;

    @GetMapping
    public ResponseEntity<List<SelectItemDTO>> listar() {
        List<SelectItemDTO> categorias = categoriaServico.listar();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }
}
