package com.projetoEletro.api.dto.post;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PontoColetaPostDTO {


    private Long id;
    private String nome;
    private String latitude;
    private String longitude;
    private String endereco;
    private String horario;

}
