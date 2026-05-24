package com.projetoEletro.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projetoEletro.api.dto.post.MensagemPostDTO;
import com.projetoEletro.api.dto.put.MensagemPutDTO;
import com.projetoEletro.api.dto.response.MensagemResponseDTO;
import com.projetoEletro.domain.service.MensagemService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private MensagemService mensagemService;

    @GetMapping
    public List<MensagemResponseDTO> listarMensagens() {
        return mensagemService.listar();
    }

    @PostMapping
    public MensagemResponseDTO adicionarMensagem(@Valid @RequestBody MensagemPostDTO mensagemPostDTO) {
        return mensagemService.criar(mensagemPostDTO);
    }

    @GetMapping("/{mensagemId}")
    public Optional<MensagemResponseDTO> buscar(@PathVariable Long mensagemId) {
        return Optional.ofNullable(mensagemService.buscarPorId(mensagemId));
    }

    @PutMapping("/{mensagemId}")
    public MensagemResponseDTO atualizar(@PathVariable Long mensagemId,
                                        @Valid @RequestBody MensagemPutDTO mensagemPutDTO) {
        return mensagemService.atualizar(mensagemId, mensagemPutDTO);
    }

    @DeleteMapping("/{mensagemId}")
    public void deletar(@PathVariable Long mensagemId) {
        mensagemService.deletar(mensagemId);
    }

    @GetMapping("/anuncio/{anuncioId}")
    public List<MensagemResponseDTO> listarPorAnuncio(@PathVariable Long anuncioId) {
        return mensagemService.listarPorAnuncio(anuncioId);
    }

    @GetMapping("/email/{email}")
    public List<MensagemResponseDTO> listarPorEmail(@PathVariable String email) {
        return mensagemService.listarPorEmail(email);
    }

    @GetMapping("/conversa")
    public List<MensagemResponseDTO> listarConversa(
            @RequestParam String emailA,
            @RequestParam String emailB,
            @RequestParam(required = false) Long anuncioId) {
        return mensagemService.listarConversa(emailA, emailB, anuncioId);
    }
}
