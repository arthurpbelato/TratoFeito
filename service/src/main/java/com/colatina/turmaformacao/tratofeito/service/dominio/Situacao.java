package com.colatina.turmaformacao.tratofeito.service.dominio;

import com.colatina.turmaformacao.tratofeito.service.dominio.enums.SituacaoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_situacao")
public class Situacao implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;
}
