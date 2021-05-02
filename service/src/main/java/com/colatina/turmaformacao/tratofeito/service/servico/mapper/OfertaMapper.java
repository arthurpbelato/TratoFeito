package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta>{
}
