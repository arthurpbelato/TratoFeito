package com.colatina.turmaformacao.tratofeito.service.repositorio;

import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepositorio extends JpaRepository<Item, Long> {

}
