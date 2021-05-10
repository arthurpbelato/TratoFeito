package com.colatina.turmaformacao.tratofeito.service.seguranca;

import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.UsuarioNaoAutenticadoException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Autenticacao {

    private final UsuarioMapper usuarioMapper;

    private final UsuarioRepositorio usuarioRepositorio;

    public void validarUsuario(Long idUsuario, String token) {
        UsuarioDTO usuarioAlvo = obterUsuarioDTOPorToken(token);
        if( (usuarioAlvo != null) && (!usuarioAlvo.getId().equals(idUsuario)) ){
            throw new UsuarioNaoAutenticadoException("Falha durante a Autenticação de Usuário: Token '" + token + "' INVÁLIDO.");
        }
    }

    private UsuarioDTO obterUsuarioDTOPorToken(String token) {
        return usuarioMapper.toDto(usuarioRepositorio.findByToken(token).orElse(null));
    }
}
