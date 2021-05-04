package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioListagemMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServico {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioListagemMapper usuarioListagemMapper;
    private final UsuarioMapper usuarioMapper;

    private Usuario getUsuario(Long id){
        Usuario usuario = usuarioRepositorio.findById(id)
                .orElseThrow(() -> new RegraNegocioException("Usuario nao encontrado"));
        return usuario;
    }

    public List<UsuarioListagemDTO> listar(){
        return usuarioRepositorio.listarTodos();
    }

    public UsuarioDTO obterPorId(Long id) {
       return usuarioRepositorio.obterPorId(id);
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setToken(UUID.randomUUID().toString());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public UsuarioDTO alterar(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = getUsuario(usuario.getId());
        usuario.setToken(usuarioSalvo.getToken());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void excluir(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}
