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
public class CategoriaPostDTO {

    @NotBlank(message = "Slug é obrigatório")
    private String slug;

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String icone;
}
