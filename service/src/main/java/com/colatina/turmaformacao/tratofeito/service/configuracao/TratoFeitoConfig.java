package com.colatina.turmaformacao.tratofeito.service.configuracao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class TratoFeitoConfig {

    @Bean
    @ConfigurationProperties("spring.mail")
    public JavaMailSender getJavaMailSender() {
        JavaMailSenderImpl javaMailSenderImpl = new JavaMailSenderImpl();
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.starttls.enable", "true");
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.starttls.required", "true");
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.auth", "true");
        javaMailSenderImpl.getJavaMailProperties().setProperty("mail.smtp.quitwait", "false");
        return javaMailSenderImpl;
    }
}
