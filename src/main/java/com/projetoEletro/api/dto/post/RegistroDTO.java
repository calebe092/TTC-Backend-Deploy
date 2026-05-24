package com.projetoEletro.api.dto.post;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegistroDTO {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String whatsapp;

    private String email;
    private String senha;
    private String foto;
}
