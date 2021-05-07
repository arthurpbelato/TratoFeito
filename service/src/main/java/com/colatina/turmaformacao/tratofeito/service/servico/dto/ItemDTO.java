package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private Long id;
    private String nome;
    private String descricao;
    private byte[] foto;
    private Boolean disponibilidade;
    private String situacao;
    private Long idUsuario;
    private Long idCategoria;
}
