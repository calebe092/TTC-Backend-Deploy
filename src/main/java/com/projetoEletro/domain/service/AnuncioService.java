package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.put.AnuncioPutDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;

import java.util.List;

public interface AnuncioService {
    List<AnuncioResponseDTO> listarAnuncios();
    AnuncioResponseDTO salvarAnuncio(AnuncioPostDTO anuncioPostDTO);
    AnuncioResponseDTO buscarAnuncioPorId(Long id);
    List<AnuncioResponseDTO> buscarAnunciosPorUsuario(Long usuarioId);
    List<AnuncioResponseDTO> buscarAnunciosPorCategoria(Long categoriaId);
    AnuncioResponseDTO atualizarAnuncio(Long id, AnuncioPutDTO anuncioPutDTO);
    void deletarAnuncio(Long id);
}
