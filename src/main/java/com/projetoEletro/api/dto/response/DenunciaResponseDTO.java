package com.projetoEletro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaResponseDTO {
    private Long id;
    private String tipo;
    private String alvoEmail;
    private String alvoTitulo;
    private String motivo;
    private String descricao;
    private String denuncianteEmail;
    private String status;
    private Long usuarioId;
    private Long anuncioId;
}
