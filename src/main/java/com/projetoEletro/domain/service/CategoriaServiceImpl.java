package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.CategoriaPostDTO;
import com.projetoEletro.api.dto.put.CategoriaPutDTO;
import com.projetoEletro.api.dto.response.CategoriaResponseDTO;
import com.projetoEletro.api.mapper.CategoriaMapper;
import com.projetoEletro.domain.model.Categoria;
import com.projetoEletro.domain.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponseDTO> listarCategorias() {
        return CategoriaMapper.listCategoriaResponseDTO(categoriaRepository.findAll());
    }

    @Override
    public CategoriaResponseDTO salvarCategoria(CategoriaPostDTO categoriaPostDTO) {
        if (categoriaPostDTO == null) {
            throw new RuntimeException("CategoriaPostDTO não pode ser nulo");
        }

        Categoria categoria = CategoriaMapper.toCategoriaPostDTO(categoriaPostDTO);
        return CategoriaMapper.toCategoriaResponseDTO(categoriaRepository.save(categoria));
    }

    @Override
    public CategoriaResponseDTO buscarCategoriaPorId(Long id) {
        if (id == null) {
            throw new RuntimeException("ID da categoria não pode ser nulo");
        }

        return CategoriaMapper.toCategoriaResponseDTO(
                categoriaRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Categoria com ID " + id + " não encontrada"))
        );
    }

    @Override
    public CategoriaResponseDTO buscarCategoriaPorSlug(String slug) {
        if (slug == null || slug.isEmpty()) {
            throw new RuntimeException("Slug da categoria não pode ser nulo ou vazio");
        }

        Categoria categoria = categoriaRepository.findBySlug(slug);
        if (categoria == null) {
            throw new RuntimeException("Categoria com slug '" + slug + "' não encontrada");
        }

        return CategoriaMapper.toCategoriaResponseDTO(categoria);
    }

    @Override
    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaPutDTO categoriaPutDTO) {
        if (id == null) {
            throw new RuntimeException("ID da categoria não pode ser nulo");
        }

        if (categoriaPutDTO == null) {
            throw new RuntimeException("CategoriaPutDTO não pode ser nulo");
        }

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + id + " não encontrada"));

        categoria = CategoriaMapper.toCategoriaFromPutDTO(categoriaPutDTO, categoria);
        return CategoriaMapper.toCategoriaResponseDTO(categoriaRepository.save(categoria));
    }

    @Override
    public void deletarCategoria(Long id) {
        if (id == null) {
            throw new RuntimeException("ID da categoria não pode ser nulo");
        }

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria com ID " + id + " não encontrada"));

        categoriaRepository.deleteById(id);
    }
}
