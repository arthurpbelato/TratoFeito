package com.colatina.turmaformacao.tratofeito.service.servico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UsuarioNaoAutenticadoException extends RuntimeException {

    public UsuarioNaoAutenticadoException(final String message){
        this(message,null);
    }

    public UsuarioNaoAutenticadoException(String message, final Throwable cause) {
        super(message, cause);
    }
}
