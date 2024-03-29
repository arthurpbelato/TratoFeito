package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OfertaMapper extends EntityMapper<OfertaDTO, Oferta>{

    @Mapping(source = "item.id", target = "idItemAlvo")
    @Mapping(source = "usuario.id", target = "idUsuarioOfertante")
    OfertaDTO toDto(Oferta entity);

    @InheritInverseConfiguration
    Oferta toEntity(OfertaDTO dto);

    @AfterMapping
    default void mapItemList(@MappingTarget Oferta oferta, OfertaDTO ofertaDTO){
        List<Long> ids = ofertaDTO.getIdItensOfertados();
        List<Item> itens = ids.stream().map(Item::new).collect(Collectors.toList());
        oferta.setItensOfertados(itens);
    }

    @AfterMapping
    default void mapItemListDTO(Oferta oferta, @MappingTarget OfertaDTO ofertaDTO){
        List<Item> itens = oferta.getItensOfertados();
        List<Long> ids = itens.stream().map(Item::getId).collect(Collectors.toList());
        ofertaDTO.setIdItensOfertados(ids);
    }
}
