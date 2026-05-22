package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.PontoColeta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoColetaRepository extends JpaRepository<PontoColeta,Long> {

}
