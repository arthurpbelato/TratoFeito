package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.dto.ItemListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.ItemServico;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itens")
public class ItemRecurso {

    private final ItemServico itemServico;

    @GetMapping
    public ResponseEntity<List<ItemListagemDTO>> listar() {
        List<ItemListagemDTO> itens = itemServico.listar();
        return new ResponseEntity<>(itens, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> obterPorId(@PathVariable("id") Long id) {
        ItemDTO item = itemServico.obterPorId(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> salvar(@RequestBody ItemDTO itemDTO) {
        itemServico.salvar(itemDTO);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> alterar(@RequestBody ItemDTO itemDTO) {
        itemServico.alterar(itemDTO);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        itemServico.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
