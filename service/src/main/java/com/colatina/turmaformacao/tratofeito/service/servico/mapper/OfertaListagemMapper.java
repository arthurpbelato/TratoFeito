package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ItemMapper.class, UsuarioMapper.class, SituacaoMapper.class })
public interface OfertaListagemMapper extends EntityMapper<OfertaListagemDTO, Oferta>{


    @Mapping(source = "usuario.nome", target = "nomeUsuario")
    @Mapping(source = "situacao.descricao", target = "situacao")
    OfertaListagemDTO toDto(Oferta entity);

    @Mapping(source = "nomeUsuario" , target = "usuario.nome")
    @Mapping(source = "situacao", target = "situacao.descricao")
    Oferta toEntity(OfertaListagemDTO dto);

}
