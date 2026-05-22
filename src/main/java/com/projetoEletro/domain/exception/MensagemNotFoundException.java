package com.projetoEletro.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MensagemNotFoundException extends RuntimeException {

    public MensagemNotFoundException(Long mensagemId) {
        super("Mensagem não encontrada com id: " + mensagemId);
    }
}
