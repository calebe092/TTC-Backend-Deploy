package com.projetoEletro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String tipo;
    private String condicao;
    private String marca;
    private String foto;
    private String bairro;
    private String status;
    private LocalDateTime dataPublicacao;
    private Long usuarioId;
    private Long categoriaId;
}
