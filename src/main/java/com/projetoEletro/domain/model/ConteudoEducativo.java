package com.projetoEletro.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "conteudo_educativo")
public class ConteudoEducativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_conteudo_educativo")
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "texto", columnDefinition = "TEXT")
    private String texto;

    @Column(name = "link_video")
    private String linkVideo;

    @Column(name = "ativo")
    private Boolean ativo;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
}
