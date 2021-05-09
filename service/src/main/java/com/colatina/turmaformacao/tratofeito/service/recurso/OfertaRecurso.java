package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.seguranca.Autenticacao;
import com.colatina.turmaformacao.tratofeito.service.servico.OfertaServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ofertas")
public class OfertaRecurso {

    private final OfertaServico ofertaServico;

    private final Autenticacao autenticacao;

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

    @PutMapping("/{token}")
    public ResponseEntity<OfertaDTO> atualizar(@RequestBody OfertaDTO ofertaDTO, @PathVariable("token") String token){
        autenticacao.validarUsuario(ofertaDTO.getIdUsuarioOfertante(), token);
        OfertaDTO oferta = ofertaServico.atualizar(ofertaDTO);
        return new ResponseEntity<>(oferta, HttpStatus.OK);
    }

    @PostMapping("/{token}")
    public ResponseEntity<OfertaDTO> salvar(@RequestBody OfertaDTO ofertaDTO, @PathVariable("token") String token){
        autenticacao.validarUsuario(ofertaDTO.getIdUsuarioOfertante(), token);
        OfertaDTO oferta = ofertaServico.salvar(ofertaDTO);
        return new ResponseEntity<>(oferta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") long id){
        ofertaServico.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/aceitar/{token}")
    public ResponseEntity<Void> aceitar(@RequestBody OfertaDTO ofertaDTO, @PathVariable("token") String token){
        autenticacao.validarUsuario(ofertaDTO.getIdUsuarioAlvo(), token);
        ofertaServico.aceitar(ofertaDTO.getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/recusar/{id}")
    public ResponseEntity<Void> recusar(@PathVariable("id") Long id){
        ofertaServico.recusar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
