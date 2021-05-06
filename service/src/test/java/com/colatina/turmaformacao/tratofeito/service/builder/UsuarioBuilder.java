package com.colatina.turmaformacao.tratofeito.service.builder;


import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.UsuarioServico;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioBuilder extends ConstrutorEntidade<Usuario> {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private UsuarioServico usuarioServico;

    @Override
    public Usuario construirEntidade() {
        Usuario usuario = new Usuario();
        usuario.setNome("Usuario Teste");
        usuario.setCpf("123456789");
        usuario.setEmail("adasncao@gmail.com");
        usuario.setDataNascimento(LocalDate.now());
        return usuario;
    }

    @Override
    public Usuario persistir(Usuario entidade) {
        return usuarioMapper.toEntity(usuarioServico.salvar(usuarioMapper.toDto(entidade)));
    }
}
