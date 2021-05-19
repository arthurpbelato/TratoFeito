package com.colatina.turmaformacao.tratofeito.service.dominio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaEnum {
    COLECIONAVEL(1L, "Colecionavel"),
    EDICAO_ESPECIAL(2L, "Edição especial"),
    ANTIGUIDADE(3L, "Antiguidade"),
    ARTESANAL(4L, "Artesanal"),
    MEMORABILIA(5L, "Memorabilia"),
    ITEM_AUTOGRAFADO(6L, "Item Autografado"),
    OUTRO(7L, "Outro");

    private Long id;
    private String descricao;
}
