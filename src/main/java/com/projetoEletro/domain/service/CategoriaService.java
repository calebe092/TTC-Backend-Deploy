package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.CategoriaPostDTO;
import com.projetoEletro.api.dto.put.CategoriaPutDTO;
import com.projetoEletro.api.dto.response.CategoriaResponseDTO;

import java.util.List;

public interface CategoriaService {
    List<CategoriaResponseDTO> listarCategorias();
    CategoriaResponseDTO salvarCategoria(CategoriaPostDTO categoriaPostDTO);
    CategoriaResponseDTO buscarCategoriaPorId(Long id);
    CategoriaResponseDTO buscarCategoriaPorSlug(String slug);
    CategoriaResponseDTO atualizarCategoria(Long id, CategoriaPutDTO categoriaPutDTO);
    void deletarCategoria(Long id);
}
