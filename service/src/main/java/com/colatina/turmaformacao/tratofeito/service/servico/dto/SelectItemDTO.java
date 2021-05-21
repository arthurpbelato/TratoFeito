package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class SelectItemDTO implements Serializable {
    private Long value;
    private String label;
}
