package com.colatina.turmaformacao.tratofeito.service.repositorio;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepositorio extends JpaRepository<Item, Long> {

    @Query("Select i From Item i Where usuario.id= :id")
    public List<Item> findAllByUsuario(@Param("id") Long id);
}
