package com.projetoEletro.api.controller;


import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;
import com.projetoEletro.domain.service.AnuncioService;
import com.projetoEletro.domain.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class Usuariocontroller {

    @Autowired
    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> listar(){
        return usuarioService.listarUsuario();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioPostDTO usuarioPostDTO) {
        return usuarioService.salvarUsuario(usuarioPostDTO);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id);
    }




}
