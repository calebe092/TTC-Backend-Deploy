package com.projetoEletro.api.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



import java.time.LocalDateTime;
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
    private Boolean ativo;
    private LocalDateTime dataCriacao;
}
