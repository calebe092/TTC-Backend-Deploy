package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.UsuarioGrupoPostDTO;
import com.projetoEletro.api.dto.response.UsuarioGrupoResponseDTO;

import java.util.List;

public interface UsuarioGrupoService {
    List<UsuarioGrupoResponseDTO> listarUsuarioGrupos();
    UsuarioGrupoResponseDTO salvarUsuarioGrupo(UsuarioGrupoPostDTO usuarioGrupoPostDTO);
    UsuarioGrupoResponseDTO buscarUsuarioGrupoPorId(Long id);
    List<UsuarioGrupoResponseDTO> buscarPorUsuario(Long usuarioId);
    List<UsuarioGrupoResponseDTO> buscarPorGrupo(Long grupoId);
    void deletarUsuarioGrupo(Long id);
}
