package com.projetoEletro.api.dto.post;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MensagemPostDTO {

    @NotBlank
    private String texto;

    @NotBlank
    @Email
    private String remetenteEmail;

    @NotBlank
    private String remetenteNome;

    @NotBlank
    @Email
    private String destinatarioEmail;

    @NotBlank
    private String destinatarioNome;

    private Long anuncioId;
}
