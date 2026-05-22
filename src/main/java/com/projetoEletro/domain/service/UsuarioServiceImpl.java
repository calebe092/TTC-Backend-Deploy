package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.AnuncioPostDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.response.AnuncioResponseDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;
import com.projetoEletro.api.mapper.AnuncioMapper;
import com.projetoEletro.api.mapper.UsuarioMapper;
import com.projetoEletro.domain.model.Anuncio;
import com.projetoEletro.domain.model.Categoria;
import com.projetoEletro.domain.model.Pessoa;
import com.projetoEletro.domain.model.Usuario;
import com.projetoEletro.domain.repository.PessoaRepository;
import com.projetoEletro.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<UsuarioResponseDTO> listarUsuario() {
        return UsuarioMapper.listUsuarioResponseDTO(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponseDTO salvarUsuario(UsuarioPostDTO usuarioPostDTO) {
        if (usuarioPostDTO == null) {
            throw new RuntimeException("UsuarioPostDTO não pode ser nulo");
        }

        if (usuarioPostDTO.getPessoaId() == null) {
            throw new RuntimeException("ID da Pessoa é obrigatório");
        }

        Pessoa pessoa = pessoaRepository.findById(usuarioPostDTO.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + usuarioPostDTO.getPessoaId() + " não encontrado"));

        Usuario usuario = UsuarioMapper.toUsuarioFromPostDTO(usuarioPostDTO, pessoa);
        return UsuarioMapper.toUsuarioResponseDTO(usuarioRepository.save(usuario));

    }

    @Override
    public List<UsuarioResponseDTO> buscarUsuarioPorId(Long usuarioId) {
return
    }

}