package com.projetoEletro.api.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String nome;
    private String email;
    private String whatsapp;
    private String status;
    private LocalDateTime dataPublicacao;
    private Long usuarioId;
    private Long categoriaId;
    private String categoria;
}
