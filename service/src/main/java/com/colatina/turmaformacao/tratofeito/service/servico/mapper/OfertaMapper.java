package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemMapper.class, UsuarioMapper.class})
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta>{

    @Mapping(source = "usuarioOferta.nome", target = "nomeUsuario")
    @Mapping(source = "situacao.descricao", target = "situacao")
    OfertaDTO toDto(Oferta entity);

    @Mapping(source = "nomeUsuario" , target = "usuarioOferta.nome")
    @Mapping(source = "situacao", target = "situacao.descricao")
    Oferta toEntity(OfertaDTO dto);
}
