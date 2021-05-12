package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.ServiceApplication;
import com.colatina.turmaformacao.tratofeito.service.builder.ItemBuilder;
import com.colatina.turmaformacao.tratofeito.service.builder.OfertaBuilder;
import com.colatina.turmaformacao.tratofeito.service.builder.UsuarioBuilder;
import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.repositorio.ItemRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.seguranca.Autenticacao;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import com.colatina.turmaformacao.tratofeito.service.util.IntTestComum;
import com.colatina.turmaformacao.tratofeito.service.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(classes = {ServiceApplication.class})
@RunWith(SpringRunner.class)
@Transactional
@ExtendWith(MockitoExtension.class)
public class OfertaRecursoIT extends IntTestComum {

    private final String API_URL = "/api/ofertas";

    @Autowired
    private OfertaMapper ofertaMapper;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private OfertaRepositorio ofertaRepositorio;

    @Autowired
    private OfertaBuilder ofertaBuilder;

    @Autowired
    private UsuarioBuilder usuarioBuilder;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @Autowired
    private ItemBuilder itemBuilder;

    @BeforeEach
    public void inicializar(){
        usuarioRepositorio.deleteAll();
        itemRepositorio.deleteAll();
        ofertaRepositorio.deleteAll();
        usuarioBuilder.removerCustomizacao();
        itemBuilder.removerCustomizacao();
        ofertaBuilder.removerCustomizacao();
    }

    @Test
    public void listar() throws Exception{
        ofertaBuilder.construir();
        getMockMvc().perform(get(API_URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void salvar() throws  Exception{
        Oferta oferta = ofertaBuilder.construirEntidade();

        getMockMvc().perform(post(API_URL + "/?token=123")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ofertaMapper.toDto(oferta))))
                .andExpect(status().isCreated());
    }

    @Test
    public void obterPorId() throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(get( API_URL + "/" + oferta.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void atualizar()throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        Item item = itemBuilder.criarNovoItem();
        oferta.setItem(item);
        getMockMvc().perform(put(API_URL + "/?token=123")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(ofertaMapper.toDto(oferta))))
                .andExpect(status().isOk());
    }

    @Test
    public void excluir() throws  Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(delete(API_URL + "/"
                + oferta.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void aceitar() throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(patch(API_URL + "/aceitar/"
                + oferta.getId() + "?token=123")
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }

    @Test
    public void recusar() throws Exception{
        Oferta oferta = ofertaBuilder.construir();
        getMockMvc().perform(patch(API_URL + "/recusar/" + oferta.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isNoContent());
    }


}
