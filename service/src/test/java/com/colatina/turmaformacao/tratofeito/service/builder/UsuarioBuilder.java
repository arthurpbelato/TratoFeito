package com.colatina.turmaformacao.tratofeito.service.builder;

import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class UsuarioBuilder extends ConstrutorEntidade<Usuario>{

    @Override
    public Usuario construirEntidade() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678910");
        usuario.setEmail("teste1@gmail.com");
        usuario.setDataNascimento(LocalDate.now());
        usuario.setNome("Teste1");

        return usuario;
    }

    @Override
    public Usuario persistir(Usuario entidade) {

        return null;
    }
}
