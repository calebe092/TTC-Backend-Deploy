package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {

    List<UsuarioResponseDTO> listarUsuario();
    UsuarioResponseDTO salvarUsuario(UsuarioPostDTO usuarioPostDTO );
    UsuarioResponseDTO buscarUsuarioPorId(Long id);
}
