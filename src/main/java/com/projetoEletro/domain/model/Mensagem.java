package com.projetoEletro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "mensagem")
public class Mensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mensagem")
    private Long id;

    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    @Column(name = "remetente_email")
    private String remetenteEmail;

    @Column(name = "remetente_nome")
    private String remetenteNome;

    @Column(name = "destinatario_email")
    private String destinatarioEmail;

    @Column(name = "destinatario_nome")
    private String destinatarioNome;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
