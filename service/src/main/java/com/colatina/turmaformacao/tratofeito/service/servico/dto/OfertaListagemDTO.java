package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaListagemDTO {
    private long id;
    private ItemDTO itemDTO;
    private String nomeUsuario;
    private String situacao;

}
