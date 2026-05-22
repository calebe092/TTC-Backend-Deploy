package com.projetoEletro.api.dto.put;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaPutDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    private String icone;
}
