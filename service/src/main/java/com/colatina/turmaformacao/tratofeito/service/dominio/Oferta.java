package com.colatina.turmaformacao.tratofeito.service.dominio;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_oferta")
@AllArgsConstructor
@NoArgsConstructor
public class Oferta implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_oferta")
    @SequenceGenerator(name = "seq_oferta", allocationSize = 1, sequenceName = "seq_oferta")
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_item")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario_oferta")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

    @ManyToMany
    @JoinTable(
            name = "tb_item_ofertado",
            joinColumns = @JoinColumn(name = "id_oferta"),
            inverseJoinColumns = @JoinColumn(name = "id_item_ofertado")
    )
    private List<Item> itensOfertados;

}
