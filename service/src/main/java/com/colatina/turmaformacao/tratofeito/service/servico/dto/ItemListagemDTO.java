package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemListagemDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Boolean disponibilidade;
    private String descricaoCategoria;
    private Long idUsuario;
}
