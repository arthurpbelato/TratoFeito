package com.colatina.turmaformacao.tratofeito.service.builder;

public abstract class ConstrutorEntidade<E> {

    private Customizacao<E> customizacao;

    public E construir() {
        final E entidade = construirEntidade();
        if(isCustomizado()) {
            customizacao.executar(entidade);
        }
        return persistir(entidade);
    }

    public ConstrutorEntidade<E> customizar(Customizacao<E> entidade) {
        this.customizacao = entidade;
        return this;
    }

    public boolean isCustomizado() {
        return this.customizacao != null;
    }

    public void setCustomizacao(Customizacao<E> customizacao) {
        this.customizacao = customizacao;
    }

    public abstract E construirEntidade();
    public abstract E persistir(E entidade);
}
