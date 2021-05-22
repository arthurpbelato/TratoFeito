package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Categoria;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.SelectItemDTO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoriaSelectItemMapper extends EntityMapper<SelectItemDTO, Categoria>{
    @Mapping(source = "id", target = "value")
    @Mapping(source = "descricao", target = "label")
    SelectItemDTO toDto(Categoria entity);

    @InheritInverseConfiguration
    Categoria toEntity(SelectItemDTO dto);
}
