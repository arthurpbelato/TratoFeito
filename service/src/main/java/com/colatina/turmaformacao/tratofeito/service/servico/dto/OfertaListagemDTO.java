package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Situacao;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OfertaListagemDTO {
    private long id;
    private ItemDTO itemDTO;
    private UsuarioDTO usuarioOfertaDTO;
    private SituacaoDTO situacaoDTO;

}
