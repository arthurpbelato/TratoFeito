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

    public static Situacao getAprovada(){
        return new Situacao(SituacaoEnum.APROVADA.getId(),
                SituacaoEnum.APROVADA.getDescricao());
    }

    public static Situacao getCancelada(){
        return new Situacao(SituacaoEnum.CANCELADA.getId(),
                SituacaoEnum.CANCELADA.getDescricao());
    }

    public static Situacao getRecusada(){
        return new Situacao(SituacaoEnum.RECUSADA.getId()
                , SituacaoEnum.RECUSADA.getDescricao());
    }

    public static Situacao getAguardandoAprovacao(){
        return new Situacao(SituacaoEnum.AGUARDANDO_APROVACAO.getId(),
                SituacaoEnum.AGUARDANDO_APROVACAO.getDescricao());
    }
}
