package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.GrupoPostDTO;
import com.projetoEletro.api.dto.put.GrupoPutDTO;
import com.projetoEletro.api.dto.response.GrupoResponseDTO;

import java.util.List;

public interface GrupoService {
    List<GrupoResponseDTO> listarGrupos();
    GrupoResponseDTO salvarGrupo(GrupoPostDTO grupoPostDTO);
    GrupoResponseDTO buscarGrupoPorId(Long id);
    GrupoResponseDTO atualizarGrupo(Long id, GrupoPutDTO grupoPutDTO);
    void deletarGrupo(Long id);
}
