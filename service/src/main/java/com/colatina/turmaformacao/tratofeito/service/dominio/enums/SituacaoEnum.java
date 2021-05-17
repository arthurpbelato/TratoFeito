package com.colatina.turmaformacao.tratofeito.service.dominio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SituacaoEnum {
    AGUARDANDO_APROVACAO(1L, "Aguardando Aprovação"),
    APROVADA(2L, "Aprovada"),
    RECUSADA(3L, "Recusada"),
    CANCELADA(4L, "Cancelada");

    private Long id;
    private String descricao;
}
