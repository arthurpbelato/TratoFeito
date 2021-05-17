package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.servico.UsuarioServico;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.TokenDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/login")
public class LoginRecurso {
    private final UsuarioServico usuarioServico;

    @PostMapping
    public ResponseEntity<UsuarioDTO> logar(@RequestBody TokenDTO tokenDTO){
        UsuarioDTO usuarioDTO = usuarioServico.logar(tokenDTO);
        return new ResponseEntity<>(usuarioDTO,HttpStatus.OK);
    }
}
