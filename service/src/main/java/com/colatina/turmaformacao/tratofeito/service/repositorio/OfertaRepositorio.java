package com.colatina.turmaformacao.tratofeito.service.repositorio;

import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfertaRepositorio extends JpaRepository<Oferta, Long> {

    @Query("from Oferta o join o.itensOfertados itens where " +
            "(o.item.id = :idItem or o.item.id IN :idItensOfertados or :idItem NOT IN itens.id) " +
            " and o.id <> :idOferta " +
            "and o.situacao.id = :idSituacao" )
    public List<Oferta> obterOfertasComItemAlvoTrocado(@Param("idItem") Long idItem,
                                                       @Param("idOferta") Long idOferta,
                                                       @Param("idSituacao") Long idSitucao,
                                                       @Param("idItensOfertados") List<Long>idItensOfertados);

    @Query("Select new com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO(" +
            "o.id, i.nome, u.nome, s.descricao)" +
            " From Oferta o Join o.item i Join o.usuario u Join o.situacao s")
    public List<OfertaListagemDTO> listOferta();

    @Query("Select o From Oferta o Where id= :id")
    public Oferta findOfertaById(@Param("id") Long id);

}
