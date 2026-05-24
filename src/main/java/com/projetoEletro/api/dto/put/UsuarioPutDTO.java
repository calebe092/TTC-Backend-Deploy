package com.projetoEletro.api.dto.put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPutDTO {


    private Long id;
    private String email;
    private String senha;
    private String foto;
    private String resetToken;
    private String resetTokenExpires;
    private String whatsapp;
    private Boolean bloqueioPublicacao;
    private Boolean bloqueioChat;
    private Long pessoaId;

}