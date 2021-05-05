package com.colatina.turmaformacao.tratofeito.service.repositorio;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta, Long> {


    @Query("from Oferta o " +
            "where (o.item.id = :idItem or o.item.id IN :idItensOfertados)  and o.id <> :idOferta " +
            "and o.situacao.id = :idSituacao")
    public List<Oferta> obterOfertasComItemAlvoTrocado(@Param("idItem") Long idItem,
                                                       @Param("idOferta") Long idOferta,
                                                       @Param("idSituacao") Long idSitucao,
                                                       @Param("idItensOfertados") List<Long>idItensOfertados);

}
