package com.projetoEletro.api.mapper;

import com.projetoEletro.api.dto.post.GrupoPostDTO;
import com.projetoEletro.api.dto.put.GrupoPutDTO;
import com.projetoEletro.api.dto.response.GrupoResponseDTO;
import com.projetoEletro.domain.model.Grupo;

public class GrupoMapper {

    public static Grupo toGrupoFromPostDTO(GrupoPostDTO dto) {
        return Grupo.builder()
                .descricao(dto.getDescricao())
                .build();
    }

    public static GrupoResponseDTO toGrupoResponseDTO(Grupo grupo) {
        return GrupoResponseDTO.builder()
                .id(grupo.getId())
                .descricao(grupo.getDescricao())
                .build();
    }

    public static void updateGrupoFromPutDTO(GrupoPutDTO dto, Grupo grupo) {
        if (dto.getDescricao() != null) grupo.setDescricao(dto.getDescricao());
    }
}
