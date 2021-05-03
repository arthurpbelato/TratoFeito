package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OfertaListagemMapper extends EntityMapper<OfertaListagemDTO, Oferta>{


    @Mapping(source = "usuarioOferta.nome", target = "nomeUsuario")
    @Mapping(source = "situacao.descricao", target = "situacao")
    OfertaListagemDTO toDto(Oferta entity);

    @Mapping(source = "nomeUsuario" , target = "usuarioOferta.nome")
    @Mapping(source = "situacao", target = "situacao.descricao")
    Oferta toEntity(OfertaListagemDTO dto);

}
