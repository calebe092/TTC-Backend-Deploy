package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.ConteudoEducativo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConteudoEducativoRepository extends JpaRepository<ConteudoEducativo,Long> {
}
