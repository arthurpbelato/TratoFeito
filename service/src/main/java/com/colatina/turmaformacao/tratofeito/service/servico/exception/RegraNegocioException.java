package com.colatina.turmaformacao.tratofeito.service.servico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RegraNegocioException extends RuntimeException{

    public RegraNegocioException(final String message){
        this(message,null);
    }

    public RegraNegocioException(String message, final Throwable cause) {
        super(message, cause);
    }
}
