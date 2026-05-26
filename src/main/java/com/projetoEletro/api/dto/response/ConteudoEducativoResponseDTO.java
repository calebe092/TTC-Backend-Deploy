package com.projetoEletro.api.dto.response;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ConteudoEducativoResponseDTO {

    private Long id;
    private String titulo;
    private String categoria;
    private String texto;
    private String linkVideo;
    private String linkOriginal;
    private String imagem;
    private Boolean ativo;
    private LocalDateTime dataCriacao;
}
