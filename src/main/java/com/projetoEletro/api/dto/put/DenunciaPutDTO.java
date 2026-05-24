package com.projetoEletro.api.dto.put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaPutDTO {
    private String tipo;
    private String alvoEmail;
    private String alvoTitulo;
    private String motivo;
    private String descricao;
    private String denuncianteEmail;
    private String status;
}
