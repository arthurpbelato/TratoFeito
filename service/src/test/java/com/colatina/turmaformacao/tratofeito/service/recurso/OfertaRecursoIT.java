package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.ServiceApplication;
import com.colatina.turmaformacao.tratofeito.service.builder.OfertaBuilder;
import com.colatina.turmaformacao.tratofeito.service.builder.UsuarioBuilder;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import com.colatina.turmaformacao.tratofeito.service.util.IntTestComum;
import com.colatina.turmaformacao.tratofeito.service.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {ServiceApplication.class})
@RunWith(SpringRunner.class)
@Transactional
public class OfertaRecursoIT extends IntTestComum {

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    private OfertaRepositorio ofertaRepositorio;

    @Autowired
    private OfertaBuilder ofertaBuilder;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @BeforeEach
    public void inicializar(){
        ofertaRepositorio.deleteAll();
        ofertaBuilder.removerCustomizacao();
    }

    @Test
    public void listar() throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(get("/api/ofertas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void salvar() throws  Exception{
        Oferta oferta = ofertaBuilder.construirEntidade();
        getMockMvc().perform(post("/api/ofertas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ofertaMapper.toDto(oferta))))
                .andExpect(status().isCreated());
    }

    @Test
    public void obterPorId() throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(get( "/api/ofertas" + "/" + oferta.getId().toString())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizar()throws Exception{
        Usuario usuario = usuarioBuilder.construir();
        Oferta oferta = ofertaBuilder.customizar(o -> {
            o.setUsuario(usuario);
        }).construir();
        getMockMvc().perform(put("/api/ofertas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ofertaMapper.toDto(oferta))))
                .andExpect(status().isOk());
    }

    @Test
    public void excluir() throws  Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(delete("/api/ofertas" + "/"
                + oferta.getId().toString())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

//    @Test
//    public void aceitar() throws Exception{
//
//    }
//
//    @Test
//    public void recusar() throws Exception{
//
//    }


}
