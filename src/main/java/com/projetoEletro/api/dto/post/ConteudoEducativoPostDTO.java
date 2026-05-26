package com.projetoEletro.api.dto.post;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConteudoEducativoPostDTO {

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
