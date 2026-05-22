package com.projetoEletro.api.mapper;


import com.projetoEletro.api.dto.post.PontoColetaPostDTO;
import com.projetoEletro.api.dto.response.PontoColetaResponseDTO;
import com.projetoEletro.domain.model.PontoColeta;

import java.util.ArrayList;
import java.util.List;

public class PontoColetaMapper {

public static List<PontoColetaResponseDTO> listPontoColetaResponseDTO(List<PontoColeta> pontoColetas){
    List<PontoColetaResponseDTO> list = new ArrayList<>();
    for (PontoColeta pontoColeta : pontoColetas){
        list.add(PontoColetaResponseDTO.builder().id(pontoColeta.getId())
                .nome(pontoColeta.getNome())
                .latitude(pontoColeta.getLatitude())
                .longitude(pontoColeta.getLongitude())
                .endereco(pontoColeta.getEndereco())
                .horario(pontoColeta.getHorario())
                .build());
    }
    return list;
}

public static PontoColeta toPontoColetaPostDTO(PontoColetaPostDTO pontoColetaPostDTO) {
    return PontoColeta.builder().id(pontoColetaPostDTO.getId())
            .nome(pontoColetaPostDTO.getNome())
            .latitude(pontoColetaPostDTO.getLatitude())
            .longitude(pontoColetaPostDTO.getLongitude())
            .endereco(pontoColetaPostDTO.getEndereco())
            .horario(pontoColetaPostDTO.getHorario())
            .build();

}

public static PontoColetaResponseDTO toPontoColetaResponseDTO(PontoColeta pontoColeta){
    return PontoColetaResponseDTO.builder().id(pontoColeta.getId())
            .nome(pontoColeta.getNome())
            .latitude(pontoColeta.getLatitude())
            .longitude(pontoColeta.getLongitude())
            .endereco(pontoColeta.getEndereco())
            .horario(pontoColeta.getHorario())
            .build();
}

public static PontoColetaResponseDTO toBuscarPontoColetaDTO(PontoColeta pontoColeta){
    return PontoColetaResponseDTO.builder().id(pontoColeta.getId())
            .nome(pontoColeta.getNome())
            .latitude(pontoColeta.getLatitude())
            .longitude(pontoColeta.getLongitude())
            .endereco(pontoColeta.getEndereco())
            .horario(pontoColeta.getHorario())
            .build();
}

}
