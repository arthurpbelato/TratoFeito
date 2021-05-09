package com.colatina.turmaformacao.tratofeito.service.servico;


import com.colatina.turmaformacao.tratofeito.service.dominio.Oferta;
import com.colatina.turmaformacao.tratofeito.service.repositorio.OfertaRepositorio;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.OfertaListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaListagemMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.OfertaMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OfertaServico {

    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioMapper usuarioMapper;
    private final OfertaRepositorio ofertaRepositorio;
    private final OfertaMapper ofertaMapper;
    private final OfertaListagemMapper ofertaListagemMapper;

    private Oferta getOferta(long id){
        return ofertaRepositorio.findOfertaById(id);
    }

    public List<OfertaListagemDTO> listar(){
        return ofertaRepositorio.listOferta();
    }

    public OfertaDTO obterPorId(long id){
        Oferta oferta = getOferta(id);
        return ofertaMapper.toDto(oferta);
    }

    public OfertaDTO atualizar(OfertaDTO ofertaDTO){
        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        ofertaRepositorio.save(oferta);
        return ofertaMapper.toDto(oferta);
    }

    public OfertaDTO salvar(OfertaDTO ofertaDTO){
        Oferta oferta = ofertaMapper.toEntity(ofertaDTO);
        ofertaRepositorio.save(oferta);
        return ofertaMapper.toDto(oferta);
    }

    public void excluir(long id){
        ofertaRepositorio.deleteById(id);
    }

    public void aceitar(Long id) {
    }

    public boolean isAlvoAutenticado(Long idOferta, String token) {
        boolean result = false;
        UsuarioDTO usuarioAlvo = obterUsuarioDTOPorToken(token);
        if(usuarioAlvo != null){
            if(ofertaMapper.toDto(ofertaRepositorio.findOfertaById(idOferta)).getIdUsuario() == usuarioAlvo.getId()){
                result = true;
            }
        }
        return result;
    }

    private UsuarioDTO obterUsuarioDTOPorToken(String token) {
        return usuarioMapper.toDto(usuarioRepositorio.findUsuarioByToken(token));
    }
}
