package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.PontoColetaPostDTO;
import com.projetoEletro.api.dto.put.PontoColetaPutDTO;
import com.projetoEletro.api.dto.response.PontoColetaResponseDTO;
import com.projetoEletro.domain.service.PontoColetaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/pontosColetas")
public class PontoColetaController {

    @Autowired
    private PontoColetaService pontocoletaService;

    @GetMapping
    public List<PontoColetaResponseDTO> listar(){
        return pontocoletaService.listarPontoColeta();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PontoColetaResponseDTO adicionarPontoColeta(@Valid @RequestBody PontoColetaPostDTO pontoColetaPostDTO){
        return pontocoletaService.salvarPontocoleta(pontoColetaPostDTO);
    }

    @GetMapping("{pontoColetaId}")
    public Optional<PontoColetaResponseDTO> buscarPontoColeta(@PathVariable Long pontoColetaId){
        return Optional.ofNullable(pontocoletaService.buscarpontoColeta(pontoColetaId));
    }

    @PutMapping("{pontoColetaId}")
    public PontoColetaResponseDTO atualizar(@PathVariable Long pontoColetaId,
                                                       @Valid @RequestBody PontoColetaPutDTO pontoColetaPutDTO){
        return pontocoletaService.atualizarPontoColeta(pontoColetaId,pontoColetaPutDTO);
    }


    @DeleteMapping("/{pontoColetaId}")
    public void deletar(@PathVariable Long pontoColetaId){
        pontocoletaService.deletarPontoColeta(pontoColetaId);
    }

}
