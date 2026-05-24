package com.projetoEletro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioGrupoResponseDTO {
    private Long id;
    private Long usuarioId;
    private Long grupoId;
}
