package com.projetoEletro.api.mapper;

import java.time.LocalDateTime;

import com.projetoEletro.api.dto.post.DenunciaPostDTO;
import com.projetoEletro.api.dto.put.DenunciaPutDTO;
import com.projetoEletro.api.dto.response.DenunciaResponseDTO;
import com.projetoEletro.domain.model.Anuncio;
import com.projetoEletro.domain.model.Denuncia;
import com.projetoEletro.domain.model.Usuario;

public class DenunciaMapper {

    public static Denuncia toDenunciaFromPostDTO(DenunciaPostDTO dto, Usuario usuario, Anuncio anuncio) {
        return Denuncia.builder()
                .tipo(dto.getTipo())
                .alvoEmail(dto.getAlvoEmail())
                .alvoTitulo(dto.getAlvoTitulo())
                .motivo(dto.getMotivo())
                .descricao(dto.getDescricao())
                .denuncianteEmail(dto.getDenuncianteEmail())
                .status(dto.getStatus() != null ? dto.getStatus() : "pendente")
                .dataCriacao(LocalDateTime.now())
                .usuario(usuario)
                .anuncio(anuncio)
                .build();
    }

    public static DenunciaResponseDTO toDenunciaResponseDTO(Denuncia denuncia) {
        return DenunciaResponseDTO.builder()
                .id(denuncia.getId())
                .tipo(denuncia.getTipo())
                .alvoEmail(denuncia.getAlvoEmail())
                .alvoTitulo(denuncia.getAlvoTitulo())
                .motivo(denuncia.getMotivo())
                .descricao(denuncia.getDescricao())
                .denuncianteEmail(denuncia.getDenuncianteEmail())
                .status(denuncia.getStatus())
                .usuarioId(denuncia.getUsuario() != null ? denuncia.getUsuario().getId() : null)
                .anuncioId(denuncia.getAnuncio() != null ? denuncia.getAnuncio().getId() : null)
                .dataCriacao(denuncia.getDataCriacao())
                .build();
    }

    public static void updateDenunciaFromPutDTO(DenunciaPutDTO dto, Denuncia denuncia) {
        if (dto.getTipo() != null) denuncia.setTipo(dto.getTipo());
        if (dto.getAlvoEmail() != null) denuncia.setAlvoEmail(dto.getAlvoEmail());
        if (dto.getAlvoTitulo() != null) denuncia.setAlvoTitulo(dto.getAlvoTitulo());
        if (dto.getMotivo() != null) denuncia.setMotivo(dto.getMotivo());
        if (dto.getDescricao() != null) denuncia.setDescricao(dto.getDescricao());
        if (dto.getDenuncianteEmail() != null) denuncia.setDenuncianteEmail(dto.getDenuncianteEmail());
        if (dto.getStatus() != null) denuncia.setStatus(dto.getStatus());
    }
}
