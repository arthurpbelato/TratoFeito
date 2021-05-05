package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class EmailDTO implements Serializable {
    private String destinatario;
    private String assunto;
    private String corpo;
    private List<String> copias = new ArrayList<>();
}
