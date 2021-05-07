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

    @NotNull(message = "Campo nome não pode ser null")
    @NotEmpty(message = "Campo nome não pode ser vazio")
    private String nome;

    @NotNull(message = "Campo data nascimento não pode ser null")
    @Past(message = "Data de nascimento deve ser no passado")
    private LocalDate dataNascimento;

    @NotNull(message = "Campo email não pode ser null")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "Campo cpf não pode ser null")
    @CPF(message = "CPF inválido")
    private String cpf;
}