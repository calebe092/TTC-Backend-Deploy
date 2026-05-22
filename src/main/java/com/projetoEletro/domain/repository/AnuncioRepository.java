package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.Anuncio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncio, Long> {
    List<Anuncio> findByUsuarioId(Long usuarioId);
    List<Anuncio> findByCategoriaId(Long categoriaId);
}
