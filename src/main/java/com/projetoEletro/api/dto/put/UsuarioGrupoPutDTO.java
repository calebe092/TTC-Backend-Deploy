package com.projetoEletro.api.dto.put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioGrupoPutDTO {
    private Long usuarioId;
    private Long grupoId;
}
