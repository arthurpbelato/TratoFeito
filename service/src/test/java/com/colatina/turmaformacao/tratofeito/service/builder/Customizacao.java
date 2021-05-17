package com.colatina.turmaformacao.tratofeito.service.builder;

public interface Customizacao<E> {

    void executar(E entidade);
}