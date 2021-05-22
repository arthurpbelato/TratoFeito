package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDetalhadoDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemDetalhadoMapper extends EntityMapper<ItemDetalhadoDTO, Item> {

    @Mapping(source = "usuario.nome", target = "usuario")
    @Mapping(source = "categoria.descricao", target = "categoria")
    ItemDetalhadoDTO toDto(Item entity);

    @InheritInverseConfiguration
    Item toEntity(ItemDetalhadoDTO dto);
}
