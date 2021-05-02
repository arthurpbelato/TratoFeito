package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfertaListagemMapper extends EntityMapper<OfertaListagemDTO, Oferta>{
}
