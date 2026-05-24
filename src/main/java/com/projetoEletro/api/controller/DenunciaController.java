package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.DenunciaPostDTO;
import com.projetoEletro.api.dto.put.DenunciaPutDTO;
import com.projetoEletro.api.dto.response.DenunciaResponseDTO;
import com.projetoEletro.domain.service.DenunciaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private DenunciaService denunciaService;

    @GetMapping
    public List<DenunciaResponseDTO> listar() {
        return denunciaService.listarDenuncias();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DenunciaResponseDTO criar(@Valid @RequestBody DenunciaPostDTO denunciaPostDTO) {
        return denunciaService.salvarDenuncia(denunciaPostDTO);
    }

    @GetMapping("/{id}")
    public DenunciaResponseDTO buscarPorId(@PathVariable Long id) {
        return denunciaService.buscarDenunciaPorId(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<DenunciaResponseDTO> buscarPorUsuario(@PathVariable Long usuarioId) {
        return denunciaService.buscarDenunciasPorUsuario(usuarioId);
    }

    @PutMapping("/{id}")
    public DenunciaResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody DenunciaPutDTO denunciaPutDTO) {
        return denunciaService.atualizarDenuncia(id, denunciaPutDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        denunciaService.deletarDenuncia(id);
    }

    @PatchMapping("/{id}/resolver")
    public DenunciaResponseDTO resolver(@PathVariable Long id) {
        return denunciaService.resolver(id);
    }
}
