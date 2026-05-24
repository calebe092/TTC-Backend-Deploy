package com.projetoEletro.api.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String email;
    private String foto;
    private String resetToken;
    private String resetTokenExpires;
    private Boolean bloqueioPublicacao;
    private Boolean bloqueioChat;
    private Long pessoaId;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String whatsapp;
    private Boolean isAdmin;
}
