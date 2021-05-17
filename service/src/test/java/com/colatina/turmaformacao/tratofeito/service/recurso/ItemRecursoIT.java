package com.colatina.turmaformacao.tratofeito.service.recurso;

import com.colatina.turmaformacao.tratofeito.service.ServiceApplication;
import com.colatina.turmaformacao.tratofeito.service.builder.ItemBuilder;
import com.colatina.turmaformacao.tratofeito.service.dominio.Item;
import com.colatina.turmaformacao.tratofeito.service.repositorio.ItemRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.ItemMapper;
import com.colatina.turmaformacao.tratofeito.service.util.IntTestComum;
import com.colatina.turmaformacao.tratofeito.service.util.TestUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ServiceApplication.class})
@Transactional
public class ItemRecursoIT extends IntTestComum {

    private final String URL = "/api/itens";

    @Autowired
    private ItemBuilder itemBuilder;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepositorio itemRepositorio;

    private int itemRepositorioCount;

    @BeforeEach
    public void inicializar(){
        itemRepositorio.deleteAll();
        itemBuilder.removerCustomizacao();
        itemRepositorioCount = (int) itemRepositorio.count();
    }

    @Test
    public void salvar() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isCreated());
    }

    @Test
    public void listar() throws Exception {
        itemBuilder.construir();
        getMockMvc().perform(get(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(itemRepositorioCount + 1)));
    }

    @Test
    public void obterPorId() throws Exception {
        Item entidade = itemBuilder.construir();
        getMockMvc().perform(get(URL + "/" + entidade.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void alterar() throws Exception {
        itemBuilder.construir();
        Item entidade = itemBuilder.criarNovoItem();
        getMockMvc().perform(put(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isOk());
    }

    @Test
    public void deletar() throws Exception {
        Item entidade = itemBuilder.construir();
        getMockMvc().perform(delete(URL + "/" + entidade.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());
    }

    @Test
    public void disponibilizarTrue() throws Exception {
        Item entidade = itemBuilder.construir();
        getMockMvc().perform(patch(URL + "/disponibilizar/"+ entidade.getId() + "/true")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.disponibilidade", is(true)));
    }

    @Test
    public void disponibilizarFalse() throws Exception {
        Item entidade = itemBuilder.construir();
        getMockMvc().perform(patch(URL + "/disponibilizar/"+ entidade.getId() + "/false")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.disponibilidade", is(false)));
    }

    @Test
    public void disponibilizarNull() throws Exception {
        Item entidade = itemBuilder.construir();
        getMockMvc().perform(patch(URL + "/disponibilizar/"+ entidade.getId() + "/null")
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarNomeNulo() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setNome(null);
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarNomeVazio() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setNome("");
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarDescricaoNulo() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setDescricao(null);
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarDescricaoVazio() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setDescricao("");
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarFotoNulo() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setFoto(null);
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarDisponibilidadeNulo() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setDisponibilidade(null);
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarSituacaoNulo() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setSituacao(null);
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void salvarSituacaoVazio() throws Exception {
        Item entidade = itemBuilder.construirEntidade();
        entidade.setSituacao("");
        getMockMvc().perform(post(URL)
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isBadRequest());
    }

}
