package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Situacao;
import com.colatina.turmaformacao.tratofeito.service.repositorio.SituacaoRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SituacaoServico {

    private final SituacaoRepositorio situacaoRepositorio;
    public Situacao getSituacao(Long id){
        return situacaoRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Situacao nao encontrada"));
    }

}
