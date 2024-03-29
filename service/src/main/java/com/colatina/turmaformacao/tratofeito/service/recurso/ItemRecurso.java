package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.servico.ItemServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDetalhadoDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDetalhadoListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemListagemDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    public ResponseEntity<ItemDTO> salvar(@RequestBody @Valid ItemDTO itemDTO) {
        ItemDTO salvo = itemServico.salvar(itemDTO);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<ItemDTO> alterar(@RequestBody @Valid ItemDTO itemDTO) {
        ItemDTO alterado = itemServico.alterar(itemDTO);
        return new ResponseEntity<>(alterado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
        itemServico.deletar(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/disponibilizar/{id}/{disponibilidade}")
    public ResponseEntity<ItemDTO> disponibilizar
            (@PathVariable("id") Long id,
             @PathVariable("disponibilidade") Boolean disponibilidade) {
        ItemDTO item = itemServico.obterPorId(id);
        return new ResponseEntity<>(itemServico.disponibilizar(item, disponibilidade),
                HttpStatus.OK);
    }

    @GetMapping("/item-detalhado/{id}")
    public ResponseEntity<ItemDetalhadoDTO> getItemDetalhado(@PathVariable("id") Long id) {
        ItemDetalhadoDTO itemDetalhadoDTO = itemServico.getItemDetalhadoDTO(id);
        return new ResponseEntity<>(itemDetalhadoDTO, HttpStatus.OK);
    }

    @GetMapping("/item-detalhado")
    public ResponseEntity<List<ItemDetalhadoListagemDTO>> listarItemDetalhado() {
        List<ItemDetalhadoListagemDTO> itemDetalhadoListagemDTO = itemServico.listarItemDetalhado();
        return new ResponseEntity<>(itemDetalhadoListagemDTO, HttpStatus.OK);
    }

    @GetMapping("/item-detalhado-usuario/{id}")
    public ResponseEntity<List<ItemDetalhadoListagemDTO>> listarItemDetalhadoUsuario(@PathVariable("id") Long id) {
        List<ItemDetalhadoListagemDTO> itemDetalhadoListagemDTO = itemServico.listarItemDetalhadoUsuario(id);
        return new ResponseEntity<>(itemDetalhadoListagemDTO, HttpStatus.OK);
    }

    @GetMapping("/item-detalhado-except-usuario/{id}")
    public ResponseEntity<List<ItemDetalhadoListagemDTO>> listarItemDetalhadoExceptUsuario(@PathVariable("id") Long id) {
        List<ItemDetalhadoListagemDTO> itemDetalhadoListagemDTO = itemServico.listarItemDetalhadoExceptUsuario(id);
        return new ResponseEntity<>(itemDetalhadoListagemDTO, HttpStatus.OK);
    }

    @GetMapping("/item-categoria-except-usuario/{categoriaId}/{usuarioId}")
    public ResponseEntity<List<ItemDetalhadoListagemDTO>> listarItemCategoriaExcetoUsuarioLogado(
            @PathVariable("categoriaId") Long categoriaId, @PathVariable("usuarioId") Long usuarioId){
        List<ItemDetalhadoListagemDTO> list = itemServico.listarPorCategoriaExcetoUsuario(categoriaId, usuarioId);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
