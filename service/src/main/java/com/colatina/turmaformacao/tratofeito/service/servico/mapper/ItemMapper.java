package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemMapper extends EntityMapper<ItemDTO, Item> {

    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "categoria.id", target = "idCategoria")
    ItemDTO toDto(Item entity);

    @InheritInverseConfiguration
    Item toEntity(ItemDTO dto);

}
