package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemIdListDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDetalhadaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDetalhadaListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ofertas")
public class OfertaRecurso {

    private final OfertaServico ofertaServico;

    @GetMapping
    public ResponseEntity<List<OfertaListagemDTO>> listar(){
        List<OfertaListagemDTO> ofertas = ofertaServico.listar();
        return new ResponseEntity<>(ofertas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfertaDTO> obterPorId(@PathVariable("id") long id){
        OfertaDTO oferta = ofertaServico.obterPorId(id);
        return new ResponseEntity<>(oferta, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<OfertaDTO> atualizar(@RequestBody OfertaDTO ofertaDTO, @RequestParam String token){
        OfertaDTO oferta = ofertaServico.atualizar(ofertaDTO, token);
        return new ResponseEntity<>(oferta, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfertaDTO> salvar(@RequestBody OfertaDTO ofertaDTO, @RequestParam("token") String token){
        OfertaDTO oferta = ofertaServico.salvar(ofertaDTO, token);
        return new ResponseEntity<>(oferta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") long id){
        ofertaServico.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/aceitar/{id}")
    public ResponseEntity<Void> aceitar(@PathVariable("id") Long id, @RequestParam String token){
        ofertaServico.aceitar(id, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/recusar/{id}")
    public ResponseEntity<Void> recusar(@PathVariable("id") Long id){
        ofertaServico.recusar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/oferta-detalhada/{id}")
    public ResponseEntity<OfertaDetalhadaDTO> getOfertaDetalhada(@PathVariable("id") Long id) {
        OfertaDetalhadaDTO ofertaDetalhadaDTO = ofertaServico.getOfertaDetalhada(id);
        return new ResponseEntity<>(ofertaDetalhadaDTO, HttpStatus.OK);
    }

    @PostMapping("/oferta-detalhada")
    public ResponseEntity<List<OfertaDetalhadaListagemDTO>> listarOfertasDetalhadas(@RequestBody ItemIdListDTO list) {
        List<OfertaDetalhadaListagemDTO> ofertaDetalhadaListagemDTO = ofertaServico.listOfertaDetalhada(list.getIds());
        return new ResponseEntity<>(ofertaDetalhadaListagemDTO, HttpStatus.OK);
    }


}
