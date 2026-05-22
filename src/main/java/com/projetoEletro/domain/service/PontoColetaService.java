package com.projetoEletro.domain.service;


import com.projetoEletro.api.dto.post.PontoColetaPostDTO;
import com.projetoEletro.api.dto.put.PontoColetaPutDTO;
import com.projetoEletro.api.dto.response.PontoColetaResponseDTO;

import java.util.List;

public interface PontoColetaService {

    List<PontoColetaResponseDTO>listarPontoColeta();
    PontoColetaResponseDTO salvarPontocoleta(PontoColetaPostDTO pontoColetaPostDTO);
    PontoColetaResponseDTO buscarpontoColeta(Long id);
    PontoColetaResponseDTO atualizarPontoColeta(Long id, PontoColetaPutDTO pontoColetaPutDTO);
    void deletarPontoColeta(Long id);
}
