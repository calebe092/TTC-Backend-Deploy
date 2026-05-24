package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.ConteudoEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConteudoEducativoRepository extends JpaRepository<ConteudoEducativo,Long> {
    List<ConteudoEducativo> findByAtivoTrue();
}
