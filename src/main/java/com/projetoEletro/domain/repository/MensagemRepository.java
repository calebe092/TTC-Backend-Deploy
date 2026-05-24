package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long> {
    List<Mensagem> findByAnuncioId(Long anuncioId);
    List<Mensagem> findByRemetenteEmailIgnoreCaseOrDestinatarioEmailIgnoreCase(String remetenteEmail, String destinatarioEmail);

    @Query("SELECT m FROM Mensagem m WHERE " +
           "(LOWER(m.remetenteEmail) = LOWER(:emailA) AND LOWER(m.destinatarioEmail) = LOWER(:emailB)) OR " +
           "(LOWER(m.remetenteEmail) = LOWER(:emailB) AND LOWER(m.destinatarioEmail) = LOWER(:emailA))")
    List<Mensagem> findConversa(@Param("emailA") String emailA, @Param("emailB") String emailB);

    @Query("SELECT m FROM Mensagem m WHERE m.anuncio.id = :anuncioId AND " +
           "((LOWER(m.remetenteEmail) = LOWER(:emailA) AND LOWER(m.destinatarioEmail) = LOWER(:emailB)) OR " +
           "(LOWER(m.remetenteEmail) = LOWER(:emailB) AND LOWER(m.destinatarioEmail) = LOWER(:emailA)))")
    List<Mensagem> findConversaPorAnuncio(@Param("anuncioId") Long anuncioId, @Param("emailA") String emailA, @Param("emailB") String emailB);
}
