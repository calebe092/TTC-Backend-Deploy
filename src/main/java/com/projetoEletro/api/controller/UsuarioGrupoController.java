package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.UsuarioGrupoPostDTO;
import com.projetoEletro.api.dto.response.UsuarioGrupoResponseDTO;
import com.projetoEletro.domain.service.UsuarioGrupoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario-grupos")
public class UsuarioGrupoController {

    @Autowired
    private UsuarioGrupoService usuarioGrupoService;

    @GetMapping
    public List<UsuarioGrupoResponseDTO> listar() {
        return usuarioGrupoService.listarUsuarioGrupos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioGrupoResponseDTO criar(@Valid @RequestBody UsuarioGrupoPostDTO usuarioGrupoPostDTO) {
        return usuarioGrupoService.salvarUsuarioGrupo(usuarioGrupoPostDTO);
    }

    @GetMapping("/{id}")
    public UsuarioGrupoResponseDTO buscarPorId(@PathVariable Long id) {
        return usuarioGrupoService.buscarUsuarioGrupoPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<UsuarioGrupoResponseDTO> buscarPorUsuario(@PathVariable Long usuarioId) {
        return usuarioGrupoService.buscarPorUsuario(usuarioId);
    }

    @GetMapping("/grupo/{grupoId}")
    public List<UsuarioGrupoResponseDTO> buscarPorGrupo(@PathVariable Long grupoId) {
        return usuarioGrupoService.buscarPorGrupo(grupoId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        usuarioGrupoService.deletarUsuarioGrupo(id);
    }
}
