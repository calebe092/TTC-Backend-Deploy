package com.projetoEletro.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.projetoEletro.api.dto.post.RegistroDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.put.UsuarioPutDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;
import com.projetoEletro.domain.service.UsuarioService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class Usuariocontroller {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
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

    @GetMapping("/email/{email}")
    public UsuarioResponseDTO buscarPorEmail(@PathVariable String email) {
        return usuarioService.buscarPorEmail(email);
    }

    @PostMapping("/login")
    public UsuarioResponseDTO login(@RequestBody Map<String, String> credenciais) {
        return usuarioService.autenticar(credenciais.get("emailOuCpf"), credenciais.get("senha"));
    }

    @PatchMapping("/{id}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long id, @RequestBody Map<String, String> dados) {
        usuarioService.alterarSenha(id, dados.get("senhaAtual"), dados.get("senhaNova"));
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioPutDTO dto) {
        return usuarioService.atualizarUsuario(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
    }

    @PostMapping("/registrar")
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO registrar(@Valid @RequestBody RegistroDTO dto) {
        return usuarioService.registrar(dto);
    }

    @GetMapping("/bloqueados")
    public List<UsuarioResponseDTO> listarBloqueados() {
        return usuarioService.listarBloqueados();
    }

    @PatchMapping("/{id}/punir")
    public UsuarioResponseDTO aplicarPunicao(@PathVariable Long id, @RequestBody Map<String, Boolean> body) {
        return usuarioService.aplicarPunicao(id, body.get("bloqueioPublicacao"), body.get("bloqueioChat"));
    }

    @PatchMapping("/{id}/punir/remover")
    public UsuarioResponseDTO removerPunicao(@PathVariable Long id) {
        return usuarioService.removerPunicao(id);
    }

    @DeleteMapping("/{id}/conta")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirContaComAnuncios(@PathVariable Long id) {
        usuarioService.excluirContaComAnuncios(id);
    }
}
