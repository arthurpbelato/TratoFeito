package com.colatina.turmaformacao.tratofeito.service.dominio;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "tb_categoria")
public class Categoria implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;
}