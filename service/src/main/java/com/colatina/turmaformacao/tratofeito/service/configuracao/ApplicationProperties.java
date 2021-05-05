package com.colatina.turmaformacao.tratofeito.service.configuracao;

import lombok.Getter;
import lombok.Setter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@RequiredArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "application.mail")
public class ApplicationProperties {
    private String enderecoRemetente;
    private String nomeRemetente;
}
