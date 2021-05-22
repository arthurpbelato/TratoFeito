package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OfertaDetalhadaDTO {
    private Long id;
    private String nomeItemAlvo;
    private String categoriaItemAlvo;
    private String nomeUsuarioOfertante;
    private List<ItemDetalhadoListagemDTO> itensOfertados;

}
