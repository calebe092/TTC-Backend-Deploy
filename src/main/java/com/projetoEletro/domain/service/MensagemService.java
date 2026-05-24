package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.MensagemPostDTO;
import com.projetoEletro.api.dto.put.MensagemPutDTO;
import com.projetoEletro.api.dto.response.MensagemResponseDTO;

import java.util.List;

public interface MensagemService {

    List<MensagemResponseDTO> listar();

    MensagemResponseDTO buscarPorId(Long mensagemId);

    MensagemResponseDTO criar(MensagemPostDTO mensagemPostDTO);

    MensagemResponseDTO atualizar(Long mensagemId, MensagemPutDTO mensagemPutDTO);

    void deletar(Long mensagemId);
    List<MensagemResponseDTO> listarPorAnuncio(Long anuncioId);
    List<MensagemResponseDTO> listarPorEmail(String email);
    List<MensagemResponseDTO> listarConversa(String emailA, String emailB, Long anuncioId);
}
