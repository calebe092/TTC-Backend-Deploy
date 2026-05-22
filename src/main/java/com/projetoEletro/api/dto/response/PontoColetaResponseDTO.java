package com.projetoEletro.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PontoColetaResponseDTO {

    private Long id;
    private String nome;
    private String latitude;
    private String longitude;
    private String endereco;
    private String horario;

}
