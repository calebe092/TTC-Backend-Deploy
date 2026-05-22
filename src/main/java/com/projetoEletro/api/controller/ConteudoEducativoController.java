package com.projetoEletro.api.controller;

import com.projetoEletro.api.dto.post.ConteudoEducativoPostDTO;
import com.projetoEletro.api.dto.put.ConteudoEducativoPutDTO;
import com.projetoEletro.api.dto.response.ConteudoEducativoResponseDTO;
import com.projetoEletro.domain.service.ConteudoEducativoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/ConteudosEducativos")
public class ConteudoEducativoController {

    @Autowired
    private ConteudoEducativoService conteudoEducativoService;

    @GetMapping
    public List<ConteudoEducativoResponseDTO> listar(){
        return conteudoEducativoService.listarConteudoEducativo();
    }

    @PostMapping
    @ResponseStatus
    public ConteudoEducativoResponseDTO adicionarConteudoEducativo(@Valid @RequestBody ConteudoEducativoPostDTO conteudoEducativoPostDTO){
        return conteudoEducativoService.salvarConteudoEducativo(conteudoEducativoPostDTO);
    }

    @GetMapping("{conteudoEducativoId}")
    public Optional<ConteudoEducativoResponseDTO> buscarConteudoEducativo(@PathVariable Long conteudoEducativoId){
        return Optional.ofNullable(conteudoEducativoService.buscarConteudoEducativoId(conteudoEducativoId));
    }

    @PutMapping("/{conteudoEducativoId}")
    public  ConteudoEducativoResponseDTO atualizarConteudoEducativo(@PathVariable Long conteudoEducativoId,
                                                                    @Valid @RequestBody ConteudoEducativoPutDTO conteudoEducativoPutDTO){
        return conteudoEducativoService.atualizarConteudoEducativo(conteudoEducativoId,conteudoEducativoPutDTO);
    }

    @DeleteMapping("/{conteudoEducativoId}")
    public void deletarConteudoEducativo(@PathVariable Long conteudoEducativoId){
        conteudoEducativoService.deletarConteudoEducativo(conteudoEducativoId);
    }



}
