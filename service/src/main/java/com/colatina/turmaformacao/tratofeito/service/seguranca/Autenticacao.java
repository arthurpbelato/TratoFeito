package com.colatina.turmaformacao.tratofeito.service.seguranca;

import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.UsuarioNaoAutenticadoException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Autenticacao {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public void validarUsuario(Long idUsuario, String token) {
        UsuarioDTO usuarioAlvo = obterUsuarioDTOPorToken(token);
        if( (usuarioAlvo != null) && (!usuarioAlvo.getId().equals(idUsuario)) ){
            throw new UsuarioNaoAutenticadoException("Falha durante a Autenticação de Usuário: Token '" + token + "' INVÁLIDO.");
        }
    }

    private UsuarioDTO obterUsuarioDTOPorToken(String token) {
        return usuarioMapper.toDto(usuarioRepositorio.findUsuarioByToken(token));
    }
}
