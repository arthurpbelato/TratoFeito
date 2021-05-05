package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta>{


    @Mapping(source = "item.id", target = "idItemAlvo")
    @Mapping(source = "usuario.id", target = "idUsuario")
    OfertaDTO toDto(Oferta entity);

    @AfterMapping
    default void mapItemList(@MappingTarget Oferta oferta, OfertaDTO ofertaDTO){
        List<Long> ids = ofertaDTO.getIdItensOfertados();
        List<Item> itens = ids.stream().map(Item::new).collect(Collectors.toList());
        oferta.setItensOfertados(itens);
    }
    @InheritInverseConfiguration
    Oferta toEntity(OfertaDTO dto);
}
