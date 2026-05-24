package com.projetoEletro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "senha", nullable = false)
    private String senha;

    @Column(name = "foto", columnDefinition = "TEXT")
    private String foto;

    @Column(name = "reset_token")
    private String resetToken;

    @Column(name = "reset_token_expires")
    private String resetTokenExpires;

    @Column(name = "bloqueio_publicacao")
    private Boolean bloqueioPublicacao;

    @Column(name = "bloqueio_chat")
    private Boolean bloqueioChat;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pessoa", referencedColumnName = "id_pessoa")
    private Pessoa pessoa;
}
