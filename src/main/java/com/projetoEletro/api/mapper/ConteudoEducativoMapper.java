package com.projetoEletro.api.mapper;

import java.util.ArrayList;
import java.util.List;

import com.projetoEletro.api.dto.post.ConteudoEducativoPostDTO;
import com.projetoEletro.api.dto.response.ConteudoEducativoResponseDTO;
import com.projetoEletro.domain.model.ConteudoEducativo;

public class ConteudoEducativoMapper {

    public static List<ConteudoEducativoResponseDTO> listConteudoEducativoResponseDTO(List<ConteudoEducativo> conteudoEducativos) {
        List<ConteudoEducativoResponseDTO> list = new ArrayList<>();
        for (ConteudoEducativo conteudoEducativo : conteudoEducativos) {
            list.add(ConteudoEducativoResponseDTO.builder().id(conteudoEducativo.getId())
                    .titulo(conteudoEducativo.getTitulo())
                    .categoria(conteudoEducativo.getCategoria())
                    .texto(conteudoEducativo.getTexto())
                    .linkVideo(conteudoEducativo.getLinkVideo())
                    .linkOriginal(conteudoEducativo.getLinkOriginal())
                    .imagem(conteudoEducativo.getImagem())
                    .ativo(conteudoEducativo.getAtivo())
                    .dataCriacao(conteudoEducativo.getDataCriacao())
                    .build());
        }
        return list;
    }

    public static ConteudoEducativo toConteudoEducativoPostDTO(ConteudoEducativoPostDTO conteudoEducativoPostDTO){
        return ConteudoEducativo.builder().id(conteudoEducativoPostDTO.getId())
                .titulo(conteudoEducativoPostDTO.getTitulo())
                .categoria(conteudoEducativoPostDTO.getCategoria())
                .texto(conteudoEducativoPostDTO.getTexto())
                .linkVideo(conteudoEducativoPostDTO.getLinkVideo())
                .linkOriginal(conteudoEducativoPostDTO.getLinkOriginal())
                .imagem(conteudoEducativoPostDTO.getImagem())
                .ativo(conteudoEducativoPostDTO.getAtivo())
                .dataCriacao(conteudoEducativoPostDTO.getDataCriacao())
                .build();

    }

    public static ConteudoEducativoResponseDTO toConteudoEducativoResponseDTO (ConteudoEducativo conteudoEducativo){
        return ConteudoEducativoResponseDTO.builder().id(conteudoEducativo.getId())
                .titulo(conteudoEducativo.getTitulo())
                .categoria(conteudoEducativo.getCategoria())
                .texto(conteudoEducativo.getTexto())
                .linkVideo(conteudoEducativo.getLinkVideo())
                .linkOriginal(conteudoEducativo.getLinkOriginal())
                .imagem(conteudoEducativo.getImagem())
                .ativo(conteudoEducativo.getAtivo())
                .dataCriacao(conteudoEducativo.getDataCriacao())
                .build();

    }

    public static ConteudoEducativoResponseDTO toBuscarConteudoEducativoDTO(ConteudoEducativo conteudoEducativo){
        return ConteudoEducativoResponseDTO.builder().id(conteudoEducativo.getId())
                .titulo(conteudoEducativo.getTitulo())
                .categoria(conteudoEducativo.getCategoria())
                .texto(conteudoEducativo.getTexto())
                .linkVideo(conteudoEducativo.getLinkVideo())
                .linkOriginal(conteudoEducativo.getLinkOriginal())
                .imagem(conteudoEducativo.getImagem())
                .ativo(conteudoEducativo.getAtivo())
                .dataCriacao(conteudoEducativo.getDataCriacao())
                .build();
    }


}