package com.projetoEletro.api.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MensagemResponseDTO {

    private Long id;
    private String texto;
    private String remetenteEmail;
    private String remetenteNome;
    private String destinatarioEmail;
    private String destinatarioNome;
    private LocalDateTime dataCriacao;
    private Long anuncioId;
}
