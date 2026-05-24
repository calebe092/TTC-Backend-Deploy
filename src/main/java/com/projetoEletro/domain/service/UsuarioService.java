package com.projetoEletro.domain.service;

import java.util.List;

import com.projetoEletro.api.dto.post.RegistroDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.put.UsuarioPutDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;

public interface UsuarioService {

    List<UsuarioResponseDTO> listarUsuario();
    UsuarioResponseDTO salvarUsuario(UsuarioPostDTO usuarioPostDTO);
    UsuarioResponseDTO buscarUsuarioPorId(Long id);
    UsuarioResponseDTO atualizarUsuario(Long id, UsuarioPutDTO dto);
    void deletarUsuario(Long id);
    UsuarioResponseDTO buscarPorEmail(String email);
    UsuarioResponseDTO autenticar(String emailOuCpf, String senha);
    void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha);
    UsuarioResponseDTO registrar(RegistroDTO dto);
    List<UsuarioResponseDTO> listarBloqueados();
    UsuarioResponseDTO aplicarPunicao(Long id, Boolean bloqueioPublicacao, Boolean bloqueioChat);
    UsuarioResponseDTO removerPunicao(Long id);
    void excluirContaComAnuncios(Long id);
}
