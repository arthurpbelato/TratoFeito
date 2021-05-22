package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDetalhadoListagemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemDetalhadoListagemMapper extends EntityMapper<ItemDetalhadoListagemDTO, Item> {

    @Mapping(source = "usuario.nome", target = "usuario")
    @Mapping(source = "categoria.descricao", target = "categoria")
    ItemDetalhadoListagemDTO toDto(Item entity);

    @InheritInverseConfiguration
    Item toEntity(ItemDetalhadoListagemDTO dto);
}
