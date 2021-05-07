package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.servico.UsuarioServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioListagemDTO;
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

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/usuarios")
public class UsuarioRecurso {

    private final UsuarioServico usuarioServico;

    @GetMapping
   public ResponseEntity<List<UsuarioListagemDTO>> listar(){
       List<UsuarioListagemDTO> usuarios = usuarioServico.listar();
       return new ResponseEntity<>(usuarios, HttpStatus.OK);
   }

   @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obterPorId(@PathVariable("id") Long id){
        UsuarioDTO usuarioDTO = usuarioServico.obterPorId(id);
        return new ResponseEntity<>(usuarioDTO, HttpStatus.OK);
   }

   @PostMapping
    public ResponseEntity<UsuarioDTO> salvar(@RequestBody @Valid UsuarioDTO usuarioDTO){
        UsuarioDTO dto = usuarioServico.salvar(usuarioDTO);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
   }

   @PutMapping
    public ResponseEntity<UsuarioDTO> alterar(@RequestBody @Valid UsuarioDTO usuarioDTO){
       UsuarioDTO dto = usuarioServico.alterar(usuarioDTO);
       return new ResponseEntity<>(dto, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable("id") Long id){
        usuarioServico.excluir(id);
        return new ResponseEntity<>(HttpStatus.OK);
   }

}
