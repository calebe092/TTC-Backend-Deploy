package com.projetoEletro.api.dto.post;

import com.projetoEletro.domain.model.Pessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioPostDTO {


    private Long id;
    private String email;
    private String senha;
    private String foto;
    private String resetToken;
    private String resetTokenExpires;
    private Boolean bloqueioPublicacao;
    private Boolean bloqueioChat;
    private Long  pessoaId;
}
