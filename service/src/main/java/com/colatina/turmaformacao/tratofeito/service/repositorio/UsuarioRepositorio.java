package com.colatina.turmaformacao.tratofeito.service.repositorio;

import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioListagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    @Query("Select new com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioListagemDTO(u.id," +
            "u.nome, " +
            "u.dataNascimento, " +
            "u.email)" +
            " from Usuario u ")
    public List<UsuarioListagemDTO> listarTodos();

    @Query("select new com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO(" +
            "u.id, " +
            "u.nome, " +
            "u.dataNascimento, " +
            "u.email, " +
            "u.cpf)" +
            " from Usuario u where u.id = :id")
    public UsuarioDTO obterPorId(@Param("id") Long id);


    public Optional<Usuario> findByCpf(String cpf);

    public Optional<Usuario> findByEmail(String email);
}
