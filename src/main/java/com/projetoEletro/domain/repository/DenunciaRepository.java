package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.Denuncia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    List<Denuncia> findByUsuarioId(Long usuarioId);
    List<Denuncia> findByAnuncioId(Long anuncioId);
}
