package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.ItemListagemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ItemListagemMapper extends EntityMapper<ItemListagemDTO, Item>{

    @Mapping(source = "usuario.id", target = "idUsuario")
    @Mapping(source = "categoria.descricao", target = "descricaoCategoria")
    ItemListagemDTO toDto(Item entity);

    @InheritInverseConfiguration
    Item toEntity(ItemListagemDTO dto);

}
