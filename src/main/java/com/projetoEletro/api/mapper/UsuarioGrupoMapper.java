package com.projetoEletro.api.mapper;

import com.projetoEletro.api.dto.post.UsuarioGrupoPostDTO;
import com.projetoEletro.api.dto.response.UsuarioGrupoResponseDTO;
import com.projetoEletro.domain.model.Grupo;
import com.projetoEletro.domain.model.Usuario;
import com.projetoEletro.domain.model.UsuarioGrupo;

public class UsuarioGrupoMapper {

    public static UsuarioGrupo toUsuarioGrupoFromPostDTO(UsuarioGrupoPostDTO dto, Usuario usuario, Grupo grupo) {
        return UsuarioGrupo.builder()
                .usuario(usuario)
                .grupo(grupo)
                .build();
    }

    public static UsuarioGrupoResponseDTO toUsuarioGrupoResponseDTO(UsuarioGrupo ug) {
        return UsuarioGrupoResponseDTO.builder()
                .id(ug.getId())
                .usuarioId(ug.getUsuario() != null ? ug.getUsuario().getId() : null)
                .grupoId(ug.getGrupo() != null ? ug.getGrupo().getId() : null)
                .build();
    }
}
