package com.projetoEletro.api.controller;


import com.projetoEletro.api.dto.post.PessoaPostDTO;
import com.projetoEletro.api.dto.put.PessoaPutDTO;
import com.projetoEletro.api.dto.response.PessoaResponseDTO;
import com.projetoEletro.domain.service.PessoaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    public List<PessoaResponseDTO> listar() {
        return pessoaService.listarPessoa();
    }

    @PostMapping
    @ResponseStatus
    public PessoaResponseDTO adicionarPessoa(@Valid @RequestBody PessoaPostDTO pessoaPostDTO) {
        return pessoaService.salvarPessoa(pessoaPostDTO);
    }

    @GetMapping("/{pessoaId}")
    public Optional<PessoaResponseDTO> busacarPessoa(@PathVariable Long pessoaId) {
        return Optional.ofNullable(pessoaService.buscarPessoaId(pessoaId));
    }

    @PutMapping("/{pessoaId}")
    public PessoaResponseDTO atualizarPessoa(@PathVariable Long pessoaId, @Valid @RequestBody PessoaPutDTO pessoaPutDTO) {
        return pessoaService.atualizarGrupoPorId(pessoaId, pessoaPutDTO);
    }

    @DeleteMapping("/{pessoaId}")
    public void deletarPessoa(@PathVariable Long pessoaId) {
        pessoaService.deletarPessoa(pessoaId);
    }

}