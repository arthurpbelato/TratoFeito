package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaDTO {
    private Long id;
    private ItemDTO itemDTO;
    private String nomeUsuario;
    private String situacao;
}
