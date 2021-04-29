package com.colatina.turmaformacao.tratofeito.service.repositorio;

import com.colatina.turmaformacao.tratofeito.service.dominio.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
}
