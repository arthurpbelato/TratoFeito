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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceApplication.class)
public class ItemRecursoIT extends IntTestComum {

    private final String API = "/api/itens";

    @Autowired
    private ItemBuilder itemBuilder;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemRepositorio itemRepositorio;

    @BeforeEach
    public void inicializar(){
        itemRepositorio.deleteAll();
    }

    @Test
    public void obterItemPorId() throws Exception {
        Item entidade = itemBuilder.construir();
        getMockMvc().perform(get(API + "/" + entidade.getId())
                .contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(TestUtil.convertObjectToJsonBytes(itemMapper.toDto(entidade))))
                .andExpect(status().isOk());
    }

}
