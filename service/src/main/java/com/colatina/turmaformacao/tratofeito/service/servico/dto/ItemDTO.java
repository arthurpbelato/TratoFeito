package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;

    @NotNull(message = "Campo nome não pode ser null.")
    @NotEmpty(message = "Campo nome não pode ser vazio.")
    private String nome;

    @NotNull(message = "Campo descrição não pode ser null.")
    @NotEmpty(message = "Campo descrição não pode ser vazio.")
    private String descricao;

    @NotNull(message = "Campo foto não pode ser null.")
    private byte[] foto;

    @NotNull(message = "Campo disponibilidade não pode ser null.")
    private Boolean disponibilidade;

    @NotNull(message = "Campo situação não pode ser null.")
    @NotEmpty(message = "Campo situação não pode ser vazio.")
    private String situacao;

    private Long idUsuario;
    private Long idCategoria;
}
