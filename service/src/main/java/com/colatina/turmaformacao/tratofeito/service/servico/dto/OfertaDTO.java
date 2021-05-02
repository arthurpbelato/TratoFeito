package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaDTO {
    private long id;
    private ItemDTO itemDTO;
    private UsuarioDTO usuarioOfertaDTO;
    private SituacaoDTO situacaoDTO;

}
