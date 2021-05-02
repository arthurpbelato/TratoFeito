package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UsuarioListagemDTO {
    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
}
