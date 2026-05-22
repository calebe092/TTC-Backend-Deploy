package com.projetoEletro.api.mapper;

import com.projetoEletro.api.dto.post.CategoriaPostDTO;
import com.projetoEletro.api.dto.put.CategoriaPutDTO;
import com.projetoEletro.api.dto.response.CategoriaResponseDTO;
import com.projetoEletro.domain.model.Categoria;

import java.util.List;
import java.util.stream.Collectors;

public class CategoriaMapper {

    public static Categoria toCategoriaPostDTO(CategoriaPostDTO dto) {
        if (dto == null) {
            return null;
        }
        Categoria categoria = new Categoria();
        categoria.setSlug(dto.getSlug());
        categoria.setNome(dto.getNome());
        categoria.setIcone(dto.getIcone());
        return categoria;
    }

    public static CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria) {
        if (categoria == null) {
            return null;
        }
        return CategoriaResponseDTO.builder()
                .id(categoria.getId())
                .slug(categoria.getSlug())
                .nome(categoria.getNome())
                .icone(categoria.getIcone())
                .build();
    }

    public static List<CategoriaResponseDTO> listCategoriaResponseDTO(List<Categoria> categorias) {
        if (categorias == null) {
            return null;
        }
        return categorias.stream()
                .map(CategoriaMapper::toCategoriaResponseDTO)
                .collect(Collectors.toList());
    }

    public static Categoria toCategoriaFromPutDTO(CategoriaPutDTO dto, Categoria categoria) {
        if (dto == null) {
            return categoria;
        }
        categoria.setNome(dto.getNome());
        categoria.setIcone(dto.getIcone());
        return categoria;
    }
}
