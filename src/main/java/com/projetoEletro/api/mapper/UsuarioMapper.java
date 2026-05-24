package com.projetoEletro.api.mapper;

import java.util.List;
import java.util.stream.Collectors;

import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;
import com.projetoEletro.domain.model.Pessoa;
import com.projetoEletro.domain.model.Usuario;

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
        usuario1.setBloqueioPublicacao(dto.getBloqueioPublicacao() != null ? dto.getBloqueioPublicacao() : false);
        usuario1.setBloqueioChat(dto.getBloqueioChat() != null ? dto.getBloqueioChat() : false);
        usuario1.setPessoa(pessoa);
        return usuario1;
    }


    public static UsuarioResponseDTO toUsuarioResponseDTO(Usuario usuario, Boolean isAdmin) {
        if (usuario == null) {
            return null;
        }
        return UsuarioResponseDTO.builder()
                .id(usuario.getId())
                .email(usuario.getEmail())
                .foto(usuario.getFoto())
                .resetToken(usuario.getResetToken())
                .resetTokenExpires(usuario.getResetTokenExpires())
                .bloqueioPublicacao(usuario.getBloqueioPublicacao())
                .bloqueioChat(usuario.getBloqueioChat())
                .pessoaId(usuario.getPessoa() != null ? usuario.getPessoa().getId() : null)
                .nome(usuario.getPessoa() != null ? usuario.getPessoa().getNome() : null)
                .cpf(usuario.getPessoa() != null ? usuario.getPessoa().getCpf() : null)
                .dataNascimento(usuario.getPessoa() != null ? usuario.getPessoa().getDataNascimento() : null)
                .whatsapp(usuario.getPessoa() != null ? usuario.getPessoa().getWhatsapp() : null)
                .isAdmin(isAdmin != null ? isAdmin : false)
                .build();
    }

    public static List<UsuarioResponseDTO> listUsuarioResponseDTO(List<Usuario> usuarios) {
        if (usuarios == null) {
            return null;
        }
        return usuarios.stream()
                .map(u -> UsuarioMapper.toUsuarioResponseDTO(u, false))
                .collect(Collectors.toList());
    }


}
