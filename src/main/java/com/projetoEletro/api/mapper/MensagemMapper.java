package com.projetoEletro.api.mapper;

import com.projetoEletro.api.dto.post.MensagemPostDTO;
import com.projetoEletro.api.dto.put.MensagemPutDTO;
import com.projetoEletro.api.dto.response.MensagemResponseDTO;
import com.projetoEletro.domain.model.Mensagem;

public interface MensagemMapper {

    static Mensagem toModel(MensagemPostDTO dto) {
        Mensagem mensagem = new Mensagem();
        mensagem.setTexto(dto.getTexto());
        mensagem.setRemetenteEmail(dto.getRemetenteEmail());
        mensagem.setRemetenteNome(dto.getRemetenteNome());
        mensagem.setDestinatarioEmail(dto.getDestinatarioEmail());
        mensagem.setDestinatarioNome(dto.getDestinatarioNome());
        return mensagem;
    }

    static void updateEntity(MensagemPutDTO dto, Mensagem mensagem) {
        mensagem.setTexto(dto.getTexto());
        mensagem.setRemetenteEmail(dto.getRemetenteEmail());
        mensagem.setRemetenteNome(dto.getRemetenteNome());
        mensagem.setDestinatarioEmail(dto.getDestinatarioEmail());
        mensagem.setDestinatarioNome(dto.getDestinatarioNome());
    }

    static MensagemResponseDTO toResponseDTO(Mensagem mensagem) {
        MensagemResponseDTO response = new MensagemResponseDTO();
        response.setId(mensagem.getId());
        response.setTexto(mensagem.getTexto());
        response.setRemetenteEmail(mensagem.getRemetenteEmail());
        response.setRemetenteNome(mensagem.getRemetenteNome());
        response.setDestinatarioEmail(mensagem.getDestinatarioEmail());
        response.setDestinatarioNome(mensagem.getDestinatarioNome());
        response.setDataCriacao(mensagem.getDataCriacao());
        return response;
    }
}
