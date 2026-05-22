package com.projetoEletro.api.mapper;


import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;
import com.projetoEletro.domain.model.Anuncio;
import com.projetoEletro.domain.model.Categoria;
import com.projetoEletro.domain.model.Pessoa;
import com.projetoEletro.domain.model.Usuario;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioMapper {

    public static Usuario toUsuarioFromPostDTO(UsuarioPostDTO dto, Pessoa pessoa) {
        if (dto == null) {
            return null;
        }
        Usuario usuario1 = new Usuario();
        usuario1.setEmail(dto.getEmail());
        usuario1.setSenha(dto.getSenha());
        usuario1.setFoto(dto.getFoto());
        usuario1.setResetToken(dto.getResetToken());
        usuario1.setResetTokenExpires(dto.getResetTokenExpires());
        usuario1.setBloqueioPublicacao(usuario1.getBloqueioPublicacao());
        usuario1.setBloqueioChat(usuario1.getBloqueioChat());
        usuario1.setPessoa(pessoa);
        return usuario1;
    }


    public static UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .foto(usuario.getFoto())
                .resetToken(usuario.getResetToken())
                .resetTokenExpires(usuario.getResetTokenExpires())
                .bloqueioPublicacao(usuario.getBloqueioPublicacao())
                .bloqueioChat(usuario.getBloqueioChat())
                .pessoaId(usuario.getPessoa() != null ? usuario.getPessoa().getId() : null)
                .build();
    }

    public static List<UsuarioResponseDTO> listUsuarioResponseDTO(List<Usuario> usuarios) {
        if (usuarios == null) {
            return null;
        }
        return usuarios.stream()
                .map(UsuarioMapper::toUsuarioResponseDTO)
                .collect(Collectors.toList());
    }


}
