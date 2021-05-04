package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemMapper.class})
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta>{

    @Mapping(source = "item", target = "itemDTO")
    @Mapping(source = "usuario.nome", target = "nomeUsuario")
    @Mapping(source = "situacao.descricao", target = "situacao")
    OfertaDTO toDto(Oferta entity);

    @Mapping(source = "itemDTO", target = "item")
    @Mapping(source = "nomeUsuario" , target = "usuario.nome")
    @Mapping(source = "situacao", target = "situacao.descricao")
    Oferta toEntity(OfertaDTO dto);
}
