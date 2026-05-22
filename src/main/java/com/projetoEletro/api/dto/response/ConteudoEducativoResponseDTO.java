package com.projetoEletro.api.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
public class ConteudoEducativoResponseDTO {

    private Long id;
    private String titulo;
    private String categoria;
    private String texto;
    private String linkVideo;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
}
