package com.colatina.turmaformacao.tratofeito.service.servico.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImagemDTO implements Serializable {
    private byte[] foto;
}
