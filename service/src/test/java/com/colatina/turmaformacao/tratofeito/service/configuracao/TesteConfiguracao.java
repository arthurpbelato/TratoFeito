package com.colatina.turmaformacao.tratofeito.service.configuracao;

import com.colatina.turmaformacao.tratofeito.service.seguranca.Autenticacao;
import com.colatina.turmaformacao.tratofeito.service.servico.EmailServico;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class TesteConfiguracao {

    @Autowired
    public void configurarServicosGerais(Autenticacao autenticacao, EmailServico email){

        Mockito.doNothing().when(autenticacao)
                .validarUsuario(Mockito.anyLong(), Mockito.anyString());
        Mockito.doNothing().when(email).sendMail(Mockito.any());
    }

    @Bean(name = "com.colatina.turmaformacao.tratofeito.service.seguranca.Autenticacao")
    @Primary
    public static Autenticacao getAutenticacao(){

        return Mockito.mock(Autenticacao.class);
    }

    @Bean(name = "com.colatina.turmaformacao.tratofeito.service.servico.EmailServico")
    @Primary
    public static EmailServico getEmail(){

        return Mockito.mock(EmailServico.class);
    }

}
