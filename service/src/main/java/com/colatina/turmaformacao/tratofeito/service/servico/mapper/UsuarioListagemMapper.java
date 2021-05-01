package com.colatina.turmaformacao.tratofeito.service.servico.mapper;

import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioListagemDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioListagemMapper extends EntityMapper<UsuarioListagemDTO, Usuario> {
}
