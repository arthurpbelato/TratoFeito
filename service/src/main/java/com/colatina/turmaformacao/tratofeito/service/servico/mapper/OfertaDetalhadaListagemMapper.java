package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDetalhadaListagemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = ItemDetalhadoListagemMapper.class)
public interface OfertaDetalhadaListagemMapper extends EntityMapper<OfertaDetalhadaListagemDTO, Oferta> {

    @Mapping(source = "item.nome", target = "nomeItemAlvo")
    @Mapping(source = "item.categoria.descricao", target = "categoriaItemAlvo")
    @Mapping(source = "usuario.nome", target = "nomeUsuarioOfertante")
    OfertaDetalhadaListagemDTO toDto(Oferta entity);

    @InheritInverseConfiguration
    Oferta toEntity(OfertaDetalhadaListagemDTO dto);
}
