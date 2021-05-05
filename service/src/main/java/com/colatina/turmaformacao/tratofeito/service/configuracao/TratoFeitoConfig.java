package com.colatina.turmaformacao.tratofeito.service.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class TratoFeitoConfig {

    @Bean
    public JavaMailSender getJavaMailSender(){
        return new JavaMailSenderImpl();
    }
}
