package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.put.AnuncioPutDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.domain.service.AnuncioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    @Autowired
    private AnuncioService anuncioService;

    @GetMapping
    public List<AnuncioResponseDTO> listar() {
        return anuncioService.listarAnuncios();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AnuncioResponseDTO criar(@Valid @RequestBody AnuncioPostDTO anuncioPostDTO) {
        return anuncioService.salvarAnuncio(anuncioPostDTO);
    }

    @GetMapping("/{id}")
    public AnuncioResponseDTO buscarPorId(@PathVariable Long id) {
        return anuncioService.buscarAnuncioPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AnuncioResponseDTO> buscarPorUsuario(@PathVariable Long usuarioId) {
        return anuncioService.buscarAnunciosPorUsuario(usuarioId);
    }

    @GetMapping("/categoria/{categoriaId}")
    public List<AnuncioResponseDTO> buscarPorCategoria(@PathVariable Long categoriaId) {
        return anuncioService.buscarAnunciosPorCategoria(categoriaId);
    }

    @PutMapping("/{id}")
    public AnuncioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AnuncioPutDTO anuncioPutDTO) {
        return anuncioService.atualizarAnuncio(id, anuncioPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        anuncioService.deletarAnuncio(id);
    }

    @GetMapping("/pendentes")
    public List<AnuncioResponseDTO> listarPendentes() {
        return anuncioService.listarPendentes();
    }

    @PatchMapping("/{id}/aprovar")
    public AnuncioResponseDTO aprovar(@PathVariable Long id) {
        return anuncioService.aprovar(id);
    }

    @PatchMapping("/{id}/rejeitar")
    public AnuncioResponseDTO rejeitar(@PathVariable Long id) {
        return anuncioService.rejeitar(id);
    }

    @GetMapping("/aprovados")
    public List<AnuncioResponseDTO> listarAprovados() {
        return anuncioService.listarAprovados();
    }
}
