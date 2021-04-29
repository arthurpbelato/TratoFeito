package com.colatina.turmaformacao.tratofeito.service.Domain;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.awt.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "tb_oferta")
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
    private Usuario usuarioOferta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_situacao")
    private Situacao situacao;

    @ManyToMany
    @JoinTable(
            name = "tb_item_ofertado",
            joinColumns = @JoinColumn(name = "id_oferta"),
            inverseJoinColumns = @JoinColumn(name = "id_item_ofertados")
    )
    private List<Item> itensOfertados;

}
