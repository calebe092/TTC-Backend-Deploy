package com.projetoEletro.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.put.AnuncioPutDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.api.mapper.AnuncioMapper;
import com.projetoEletro.domain.model.Anuncio;
import com.projetoEletro.domain.model.Categoria;
import com.projetoEletro.domain.model.Usuario;
import com.projetoEletro.domain.repository.AnuncioRepository;
import com.projetoEletro.domain.repository.CategoriaRepository;
import com.projetoEletro.domain.repository.UsuarioRepository;

@Service
public class AnuncioServiceImpl implements AnuncioService {

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<AnuncioResponseDTO> listarAnuncios() {
        return AnuncioMapper.listAnuncioResponseDTO(anuncioRepository.findAll());
    }

    @Override
    public AnuncioResponseDTO salvarAnuncio(AnuncioPostDTO anuncioPostDTO) {
        if (anuncioPostDTO == null) {
            throw new RuntimeException("AnuncioPostDTO não pode ser nulo");
        }

        Usuario usuario = null;
        if (anuncioPostDTO.getUsuarioId() != null) {
            usuario = usuarioRepository.findById(anuncioPostDTO.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuário com ID " + anuncioPostDTO.getUsuarioId() + " não encontrado"));
        }

        Categoria categoria = null;
        if (anuncioPostDTO.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(anuncioPostDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria com ID " + anuncioPostDTO.getCategoriaId() + " não encontrada"));
        } else if (anuncioPostDTO.getCategoriaSlug() != null) {
            categoria = categoriaRepository.findBySlug(anuncioPostDTO.getCategoriaSlug());
        }

        Anuncio anuncio = AnuncioMapper.toAnuncioFromPostDTO(anuncioPostDTO, usuario, categoria);
        return AnuncioMapper.toAnuncioResponseDTO(anuncioRepository.save(anuncio));
    }

    @Override
    public AnuncioResponseDTO buscarAnuncioPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("ID do anúncio não pode ser nulo");
        }

        return AnuncioMapper.toAnuncioResponseDTO(
                anuncioRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Anúncio com ID " + id + " não encontrado"))
        );
    }

    @Override
    public List<AnuncioResponseDTO> buscarAnunciosPorUsuario(Long usuarioId) {
        if (usuarioId == null) {
            throw new RuntimeException("ID do usuário não pode ser nulo");
        }

        usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioId + " não encontrado"));

        return AnuncioMapper.listAnuncioResponseDTO(anuncioRepository.findByUsuarioId(usuarioId));
    }

    @Override
    public List<AnuncioResponseDTO> buscarAnunciosPorCategoria(Long categoriaId) {
        if (categoriaId == null) {
            throw new RuntimeException("ID da categoria não pode ser nulo");
        }

        categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + categoriaId + " não encontrada"));

        return AnuncioMapper.listAnuncioResponseDTO(anuncioRepository.findByCategoriaId(categoriaId));
    }

    @Override
    public AnuncioResponseDTO atualizarAnuncio(Long id, AnuncioPutDTO anuncioPutDTO) {
        if (id == null) {
            throw new RuntimeException("ID do anúncio não pode ser nulo");
        }

        if (anuncioPutDTO == null) {
            throw new RuntimeException("AnuncioPutDTO não pode ser nulo");
        }

        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anúncio com ID " + id + " não encontrado"));

        Categoria categoria = null;
        if (anuncioPutDTO.getCategoriaId() != null) {
            categoria = categoriaRepository.findById(anuncioPutDTO.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoria com ID " + anuncioPutDTO.getCategoriaId() + " não encontrada"));
        } else if (anuncioPutDTO.getCategoriaSlug() != null) {
            categoria = categoriaRepository.findBySlug(anuncioPutDTO.getCategoriaSlug());
        }

        anuncio = AnuncioMapper.toAnuncioFromPutDTO(anuncioPutDTO, anuncio, categoria);
        return AnuncioMapper.toAnuncioResponseDTO(anuncioRepository.save(anuncio));
    }

    @Override
    public void deletarAnuncio(Long id) {
        if (id == null) {
            throw new RuntimeException("ID do anúncio não pode ser nulo");
        }

        anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anúncio com ID " + id + " não encontrado"));

        anuncioRepository.deleteById(id);
    }

    @Override
    public List<AnuncioResponseDTO> listarPendentes() {
        return AnuncioMapper.listAnuncioResponseDTO(anuncioRepository.findByStatus("pendente"));
    }

    @Override
    public AnuncioResponseDTO aprovar(Long id) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anúncio com ID " + id + " não encontrado"));
        anuncio.setStatus("aprovado");
        return AnuncioMapper.toAnuncioResponseDTO(anuncioRepository.save(anuncio));
    }

    @Override
    public AnuncioResponseDTO rejeitar(Long id) {
        Anuncio anuncio = anuncioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Anúncio com ID " + id + " não encontrado"));
        anuncio.setStatus("rejeitado");
        return AnuncioMapper.toAnuncioResponseDTO(anuncioRepository.save(anuncio));
    }

    @Override
    public List<AnuncioResponseDTO> listarAprovados() {
        return AnuncioMapper.listAnuncioResponseDTO(anuncioRepository.findByStatus("aprovado"));
    }
}
