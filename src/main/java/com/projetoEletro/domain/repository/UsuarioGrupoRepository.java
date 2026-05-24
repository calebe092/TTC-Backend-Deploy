package com.projetoEletro.domain.repository;

import com.projetoEletro.domain.model.UsuarioGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioGrupoRepository extends JpaRepository<UsuarioGrupo, Long> {
    List<UsuarioGrupo> findByUsuarioId(Long usuarioId);
    List<UsuarioGrupo> findByGrupoId(Long grupoId);
}
