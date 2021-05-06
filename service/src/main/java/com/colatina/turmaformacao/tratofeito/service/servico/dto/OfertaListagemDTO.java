package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfertaListagemDTO {
    private Long id;
    private String nomeItem;
    private String nomeUsuario;
    private String situacao;

}
