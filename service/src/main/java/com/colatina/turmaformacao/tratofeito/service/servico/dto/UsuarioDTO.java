package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotNull
    @NotEmpty
    private String nome;

    @Past
    @NotNull
    @NotEmpty
    private LocalDate dataNascimento;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @CPF
    @NotNull
    @NotEmpty
    private String cpf;
}