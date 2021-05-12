package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OfertaDTO {
    private Long id;
    private Long idItemAlvo;
//    private Long idUsuarioAlvo;
    private Long idUsuarioOfertante;
    private List<Long> idItensOfertados;
}
