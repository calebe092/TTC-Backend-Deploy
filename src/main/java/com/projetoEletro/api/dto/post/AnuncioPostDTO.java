package com.projetoEletro.api.dto.post;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AnuncioPostDTO {

    @NotBlank(message = "Título é obrigatório")
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
    private Long usuarioId;
    private Long categoriaId;
    private String categoriaSlug;
}
