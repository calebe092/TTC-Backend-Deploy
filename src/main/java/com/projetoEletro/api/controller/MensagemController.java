package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.MensagemPostDTO;
import com.projetoEletro.api.dto.put.MensagemPutDTO;
import com.projetoEletro.api.dto.response.MensagemResponseDTO;
import com.projetoEletro.domain.service.MensagemService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
@RestController
@RequestMapping("/mensagens")
public class MensagemController {

    private final MensagemService mensagemService;

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
}
