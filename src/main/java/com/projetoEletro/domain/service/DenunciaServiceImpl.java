package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.DenunciaPostDTO;
import com.projetoEletro.api.dto.put.DenunciaPutDTO;
import com.projetoEletro.api.dto.response.DenunciaResponseDTO;
import com.projetoEletro.api.mapper.DenunciaMapper;
import com.projetoEletro.domain.model.Anuncio;
import com.projetoEletro.domain.model.Denuncia;
import com.projetoEletro.domain.model.Usuario;
import com.projetoEletro.domain.repository.AnuncioRepository;
import com.projetoEletro.domain.repository.DenunciaRepository;
import com.projetoEletro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DenunciaServiceImpl implements DenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Override
    public List<DenunciaResponseDTO> listarDenuncias() {
        return denunciaRepository.findAll().stream()
                .map(DenunciaMapper::toDenunciaResponseDTO)
                .toList();
    }

    @Override
    public DenunciaResponseDTO salvarDenuncia(DenunciaPostDTO denunciaPostDTO) {
        if (denunciaPostDTO == null) {
            throw new RuntimeException("DenunciaPostDTO não pode ser nulo");
        }

        Usuario usuario = null;
        if (denunciaPostDTO.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(denunciaPostDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário com ID " + denunciaPostDTO.getUsuarioId() + " não encontrado"));
        }

        Anuncio anuncio = null;
        if (denunciaPostDTO.getAnuncioId() != null) {
            anuncio = anuncioRepository.findById(denunciaPostDTO.getAnuncioId())
                    .orElseThrow(() -> new RuntimeException("Anúncio com ID " + denunciaPostDTO.getAnuncioId() + " não encontrado"));
        }

        Denuncia denuncia = DenunciaMapper.toDenunciaFromPostDTO(denunciaPostDTO, usuario, anuncio);
        return DenunciaMapper.toDenunciaResponseDTO(denunciaRepository.save(denuncia));
    }

    @Override
    public DenunciaResponseDTO buscarDenunciaPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("ID da denúncia não pode ser nulo");
        }

        return DenunciaMapper.toDenunciaResponseDTO(
                denunciaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Denúncia com ID " + id + " não encontrada"))
        );
    }

    @Override
    public List<DenunciaResponseDTO> buscarDenunciasPorUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new RuntimeException("ID do usuário não pode ser nulo");
        }

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioId + " não encontrado"));

        return denunciaRepository.findByUsuarioId(usuarioId).stream()
                .map(DenunciaMapper::toDenunciaResponseDTO)
                .toList();
    }

    @Override
    public DenunciaResponseDTO atualizarDenuncia(Long id, DenunciaPutDTO denunciaPutDTO) {
        if (id == null) {
            throw new RuntimeException("ID da denúncia não pode ser nulo");
        }

        if (denunciaPutDTO == null) {
            throw new RuntimeException("DenunciaPutDTO não pode ser nulo");
        }

        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia com ID " + id + " não encontrada"));

        DenunciaMapper.updateDenunciaFromPutDTO(denunciaPutDTO, denuncia);
        return DenunciaMapper.toDenunciaResponseDTO(denunciaRepository.save(denuncia));
    }

    @Override
    public void deletarDenuncia(Long id) {
        if (id == null) {
            throw new RuntimeException("ID da denúncia não pode ser nulo");
        }

        denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia com ID " + id + " não encontrada"));

        denunciaRepository.deleteById(id);
    }

    @Override
    public DenunciaResponseDTO resolver(Long id) {
        Denuncia denuncia = denunciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Denúncia com ID " + id + " não encontrada"));

        denuncia.setStatus("RESOLVIDA");
        return DenunciaMapper.toDenunciaResponseDTO(denunciaRepository.save(denuncia));
    }
}
