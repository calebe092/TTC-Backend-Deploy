package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.GrupoPostDTO;
import com.projetoEletro.api.dto.put.GrupoPutDTO;
import com.projetoEletro.api.dto.response.GrupoResponseDTO;
import com.projetoEletro.domain.service.GrupoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/grupos")
public class GrupoController {

    @Autowired
    private GrupoService grupoService;

    @GetMapping
    public List<GrupoResponseDTO> listar() {
        return grupoService.listarGrupos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GrupoResponseDTO criar(@Valid @RequestBody GrupoPostDTO grupoPostDTO) {
        return grupoService.salvarGrupo(grupoPostDTO);
    }

    @GetMapping("/{id}")
    public GrupoResponseDTO buscarPorId(@PathVariable Long id) {
        return grupoService.buscarGrupoPorId(id);
    }

    @PutMapping("/{id}")
    public GrupoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody GrupoPutDTO grupoPutDTO) {
        return grupoService.atualizarGrupo(id, grupoPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        grupoService.deletarGrupo(id);
    }
}
