package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemDetalhadoListagemDTO {

    private Long id;

    private String nome;

    private String descricao;

    private byte[] foto;

    private Boolean disponibilidade;

    private String situacao;

    private String usuario;

    private String categoria;
}
