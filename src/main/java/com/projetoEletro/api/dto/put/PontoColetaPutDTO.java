package com.projetoEletro.api.dto.put;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PontoColetaPutDTO {

    private Long id;
    private String nome;
    private String latitude;
    private String longitude;
    private String endereco;
    private String horario;

}
