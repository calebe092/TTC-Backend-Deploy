package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.UsuarioGrupoPostDTO;
import com.projetoEletro.api.dto.response.UsuarioGrupoResponseDTO;
import com.projetoEletro.api.mapper.UsuarioGrupoMapper;
import com.projetoEletro.domain.model.Grupo;
import com.projetoEletro.domain.model.Usuario;
import com.projetoEletro.domain.model.UsuarioGrupo;
import com.projetoEletro.domain.repository.GrupoRepository;
import com.projetoEletro.domain.repository.UsuarioGrupoRepository;
import com.projetoEletro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioGrupoServiceImpl implements UsuarioGrupoService {

    @Autowired
    private UsuarioGrupoRepository usuarioGrupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Override
    public List<UsuarioGrupoResponseDTO> listarUsuarioGrupos() {
        return usuarioGrupoRepository.findAll().stream()
                .map(UsuarioGrupoMapper::toUsuarioGrupoResponseDTO)
                .toList();
    }

    @Override
    public UsuarioGrupoResponseDTO salvarUsuarioGrupo(UsuarioGrupoPostDTO usuarioGrupoPostDTO) {
        if (usuarioGrupoPostDTO == null) {
            throw new RuntimeException("UsuarioGrupoPostDTO não pode ser nulo");
        }

        Usuario usuario = usuarioRepository.findById(usuarioGrupoPostDTO.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioGrupoPostDTO.getUsuarioId() + " não encontrado"));

        Grupo grupo = grupoRepository.findById(usuarioGrupoPostDTO.getGrupoId())
                .orElseThrow(() -> new RuntimeException("Grupo com ID " + usuarioGrupoPostDTO.getGrupoId() + " não encontrado"));

        UsuarioGrupo usuarioGrupo = UsuarioGrupoMapper.toUsuarioGrupoFromPostDTO(usuarioGrupoPostDTO, usuario, grupo);
        return UsuarioGrupoMapper.toUsuarioGrupoResponseDTO(usuarioGrupoRepository.save(usuarioGrupo));
    }

    @Override
    public UsuarioGrupoResponseDTO buscarUsuarioGrupoPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        return UsuarioGrupoMapper.toUsuarioGrupoResponseDTO(
                usuarioGrupoRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("UsuarioGrupo com ID " + id + " não encontrado"))
        );
    }

    @Override
    public List<UsuarioGrupoResponseDTO> buscarPorUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new RuntimeException("ID do usuário não pode ser nulo");
        }

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioId + " não encontrado"));

        return usuarioGrupoRepository.findByUsuarioId(usuarioId).stream()
                .map(UsuarioGrupoMapper::toUsuarioGrupoResponseDTO)
                .toList();
    }

    @Override
    public List<UsuarioGrupoResponseDTO> buscarPorGrupo(Long grupoId) {
        if (grupoId == null) {
            throw new RuntimeException("ID do grupo não pode ser nulo");
        }

        grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RuntimeException("Grupo com ID " + grupoId + " não encontrado"));

        return usuarioGrupoRepository.findByGrupoId(grupoId).stream()
                .map(UsuarioGrupoMapper::toUsuarioGrupoResponseDTO)
                .toList();
    }

    @Override
    public void deletarUsuarioGrupo(Long id) {
        if (id == null) {
            throw new RuntimeException("ID não pode ser nulo");
        }

        usuarioGrupoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsuarioGrupo com ID " + id + " não encontrado"));

        usuarioGrupoRepository.deleteById(id);
    }
}
