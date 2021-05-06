package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.ServiceApplication;
import com.colatina.turmaformacao.tratofeito.service.builder.OfertaBuilder;
import com.colatina.turmaformacao.tratofeito.service.builder.UsuarioBuilder;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import com.colatina.turmaformacao.tratofeito.service.util.IntTestComum;
import com.colatina.turmaformacao.tratofeito.service.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    }

    @Test
    public void listagem() throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(get("/api/ofertas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
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
        Oferta oferta = ofertaBuilder.construir();
        Usuario usuario = usuarioBuilder.construir();
        oferta.setUsuario(usuarioBuilder.customizar(u -> {
            u.setEmail("teste321@gmail.com");
        }).construir());
        getMockMvc().perform(put("/api/ofertas")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ofertaMapper.toDto(oferta))))
                .andExpect(status().isOk());
    }

    @Test
    public void excluir() throws  Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(MockMvcRequestBuilders
                .delete("/api/ofertas" + "/" + oferta.getId().toString())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }



}
