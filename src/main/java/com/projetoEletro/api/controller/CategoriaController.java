package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.CategoriaPostDTO;
import com.projetoEletro.api.dto.put.CategoriaPutDTO;
import com.projetoEletro.api.dto.response.CategoriaResponseDTO;
import com.projetoEletro.domain.service.CategoriaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> listar() {
        return categoriaService.listarCategorias();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoriaResponseDTO criar(@Valid @RequestBody CategoriaPostDTO categoriaPostDTO) {
        return categoriaService.salvarCategoria(categoriaPostDTO);
    }

    @GetMapping("/{id}")
    public CategoriaResponseDTO buscarPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id);
    }

    @GetMapping("/slug/{slug}")
    public CategoriaResponseDTO buscarPorSlug(@PathVariable String slug) {
        return categoriaService.buscarCategoriaPorSlug(slug);
    }

    @PutMapping("/{id}")
    public CategoriaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CategoriaPutDTO categoriaPutDTO) {
        return categoriaService.atualizarCategoria(id, categoriaPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        categoriaService.deletarCategoria(id);
    }
}
