package com.colatina.turmaformacao.tratofeito.service.servico;

import com.colatina.turmaformacao.tratofeito.service.dominio.Usuario;
import com.colatina.turmaformacao.tratofeito.service.repositorio.UsuarioRepositorio;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.EmailDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.TokenDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.dto.UsuarioListagemDTO;
import com.colatina.turmaformacao.tratofeito.service.servico.exception.RegraNegocioException;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioListagemMapper;
import com.colatina.turmaformacao.tratofeito.service.servico.mapper.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioServico {
    private final UsuarioRepositorio usuarioRepositorio;
    private final UsuarioListagemMapper usuarioListagemMapper;
    private final UsuarioMapper usuarioMapper;
    private final EmailServico emailServico;

    private void isDuplicado(UsuarioDTO dto){
        Optional<Usuario> usuarioOptional = usuarioRepositorio.findByCpf(dto.getCpf());
        if(usuarioOptional.isPresent() && !usuarioOptional.get().getId().equals(dto.getId())){
                throw new RegraNegocioException("CPF Duplicado");
        }

        Optional<Usuario> usuarioOptional2 = usuarioRepositorio.findByEmail(dto.getEmail());
        if(usuarioOptional2.isPresent() && !usuarioOptional2.get().getId().equals(dto.getId())){
            throw new RegraNegocioException("Email Duplicado");
        }
    }

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
        isDuplicado(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        usuario.setToken(UUID.randomUUID().toString());
        usuarioRepositorio.save(usuario);
        emailServico.sendMail(criarEmail(usuario));
        return usuarioMapper.toDto(usuario);
    }

    private EmailDTO criarEmail(Usuario usuario){
        EmailDTO email = new EmailDTO();
        email.setAssunto("Cadastro de Usuários");
        email.setCorpo("Cadastro realizado com sucesso. Seu Token é: " + usuario.getToken());
        email.setDestinatario(usuario.getEmail());
        return email;
    }

    public UsuarioDTO alterar(UsuarioDTO usuarioDTO) {
        isDuplicado(usuarioDTO);
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        Usuario usuarioSalvo = getUsuario(usuario.getId());
        usuario.setToken(usuarioSalvo.getToken());
        usuarioRepositorio.save(usuario);
        return usuarioMapper.toDto(usuario);
    }

    public void excluir(Long id) {
        usuarioRepositorio.deleteById(id);
    }


    public UsuarioDTO logar(TokenDTO tokenDTO) {
        return usuarioMapper.toDto(
                usuarioRepositorio.findByToken(tokenDTO.getToken())
                        .orElseThrow(() -> new RegraNegocioException("Usuário não encontrado")));
    }
}
