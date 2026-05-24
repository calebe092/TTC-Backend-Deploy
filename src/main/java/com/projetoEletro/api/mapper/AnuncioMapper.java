package com.projetoEletro.api.mapper;

import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.put.AnuncioPutDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.domain.model.Anuncio;
import com.projetoEletro.domain.model.Categoria;
import com.projetoEletro.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AnuncioMapper {

    public static Anuncio toAnuncioFromPostDTO(AnuncioPostDTO dto, Usuario usuario, Categoria categoria) {
        if (dto == null) {
            return null;
        }
        Anuncio anuncio = new Anuncio();
        anuncio.setTitulo(dto.getTitulo());
        anuncio.setDescricao(dto.getDescricao());
        anuncio.setTipo(dto.getTipo());
        anuncio.setCondicao(dto.getCondicao());
        anuncio.setMarca(dto.getMarca());
        anuncio.setFoto(dto.getFoto());
        anuncio.setBairro(dto.getBairro());
        anuncio.setNome(dto.getNome());
        anuncio.setEmail(dto.getEmail());
        anuncio.setWhatsapp(dto.getWhatsapp());
        anuncio.setStatus(dto.getStatus() != null ? dto.getStatus() : "pendente");
        anuncio.setDataPublicacao(LocalDateTime.now());
        anuncio.setUsuario(usuario);
        anuncio.setCategoria(categoria);
        return anuncio;
    }

    public static AnuncioResponseDTO toAnuncioResponseDTO(Anuncio anuncio) {
        if (anuncio == null) {
            return null;
        }
        return AnuncioResponseDTO.builder()
                .id(anuncio.getId())
                .titulo(anuncio.getTitulo())
                .descricao(anuncio.getDescricao())
                .tipo(anuncio.getTipo())
                .condicao(anuncio.getCondicao())
                .marca(anuncio.getMarca())
                .foto(anuncio.getFoto())
                .bairro(anuncio.getBairro())
                .nome(anuncio.getNome())
                .email(anuncio.getEmail())
                .whatsapp(anuncio.getWhatsapp())
                .status(anuncio.getStatus())
                .dataPublicacao(anuncio.getDataPublicacao())
                .usuarioId(anuncio.getUsuario() != null ? anuncio.getUsuario().getId() : null)
                .categoriaId(anuncio.getCategoria() != null ? anuncio.getCategoria().getId() : null)
                .build();
    }

    public static List<AnuncioResponseDTO> listAnuncioResponseDTO(List<Anuncio> anuncios) {
        if (anuncios == null) {
            return null;
        }
        return anuncios.stream()
                .map(AnuncioMapper::toAnuncioResponseDTO)
                .collect(Collectors.toList());
    }

    public static Anuncio toAnuncioFromPutDTO(AnuncioPutDTO dto, Anuncio anuncio, Categoria categoria) {
        if (dto == null) {
            return anuncio;
        }
        anuncio.setTitulo(dto.getTitulo());
        anuncio.setDescricao(dto.getDescricao());
        anuncio.setTipo(dto.getTipo());
        anuncio.setCondicao(dto.getCondicao());
        anuncio.setMarca(dto.getMarca());
        anuncio.setFoto(dto.getFoto());
        anuncio.setBairro(dto.getBairro());
        anuncio.setNome(dto.getNome());
        anuncio.setEmail(dto.getEmail());
        anuncio.setWhatsapp(dto.getWhatsapp());
        anuncio.setStatus(dto.getStatus());
        if (categoria != null) {
            anuncio.setCategoria(categoria);
        }
        return anuncio;
    }
}
