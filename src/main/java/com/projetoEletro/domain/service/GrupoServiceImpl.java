package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.GrupoPostDTO;
import com.projetoEletro.api.dto.put.GrupoPutDTO;
import com.projetoEletro.api.dto.response.GrupoResponseDTO;
import com.projetoEletro.api.mapper.GrupoMapper;
import com.projetoEletro.domain.model.Grupo;
import com.projetoEletro.domain.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoServiceImpl implements GrupoService {

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public List<GrupoResponseDTO> listarGrupos() {
        return grupoRepository.findAll().stream()
                .map(GrupoMapper::toGrupoResponseDTO)
                .toList();
    }

    @Override
    public GrupoResponseDTO salvarGrupo(GrupoPostDTO grupoPostDTO) {
        if (grupoPostDTO == null) {
            throw new RuntimeException("GrupoPostDTO não pode ser nulo");
        }

        Grupo grupo = GrupoMapper.toGrupoFromPostDTO(grupoPostDTO);
        return GrupoMapper.toGrupoResponseDTO(grupoRepository.save(grupo));
    }

    @Override
    public GrupoResponseDTO buscarGrupoPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("ID do grupo não pode ser nulo");
        }

        return GrupoMapper.toGrupoResponseDTO(
                grupoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Grupo com ID " + id + " não encontrado"))
        );
    }

    @Override
    public GrupoResponseDTO atualizarGrupo(Long id, GrupoPutDTO grupoPutDTO) {
        if (id == null) {
            throw new RuntimeException("ID do grupo não pode ser nulo");
        }

        if (grupoPutDTO == null) {
            throw new RuntimeException("GrupoPutDTO não pode ser nulo");
        }

        Grupo grupo = grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo com ID " + id + " não encontrado"));

        GrupoMapper.updateGrupoFromPutDTO(grupoPutDTO, grupo);
        return GrupoMapper.toGrupoResponseDTO(grupoRepository.save(grupo));
    }

    @Override
    public void deletarGrupo(Long id) {
        if (id == null) {
            throw new RuntimeException("ID do grupo não pode ser nulo");
        }

        grupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Grupo com ID " + id + " não encontrado"));

        grupoRepository.deleteById(id);
    }
}
