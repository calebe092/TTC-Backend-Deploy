package com.projetoEletro.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.projetoEletro.api.dto.post.RegistroDTO;
import com.projetoEletro.api.dto.post.UsuarioPostDTO;
import com.projetoEletro.api.dto.put.UsuarioPutDTO;
import com.projetoEletro.api.dto.response.UsuarioResponseDTO;
import com.projetoEletro.api.mapper.UsuarioMapper;
import com.projetoEletro.domain.model.Pessoa;
import com.projetoEletro.domain.model.Usuario;
import com.projetoEletro.domain.model.UsuarioGrupo;
import com.projetoEletro.domain.repository.AnuncioRepository;
import com.projetoEletro.domain.repository.PessoaRepository;
import com.projetoEletro.domain.repository.UsuarioGrupoRepository;
import com.projetoEletro.domain.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Long GRUPO_ADMIN_ID = 1L;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioGrupoRepository usuarioGrupoRepository;

    @Autowired
    private AnuncioRepository anuncioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Boolean isAdmin(Long usuarioId) {
        List<UsuarioGrupo> grupos = usuarioGrupoRepository.findByUsuarioId(usuarioId);
        return grupos.stream().anyMatch(ug -> ug.getGrupo() != null && GRUPO_ADMIN_ID.equals(ug.getGrupo().getId()));
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuario() {
        return usuarioRepository.findAll().stream()
                .map(u -> UsuarioMapper.toUsuarioResponseDTO(u, isAdmin(u.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO salvarUsuario(UsuarioPostDTO usuarioPostDTO) {
        if (usuarioPostDTO == null) {
            throw new RuntimeException("UsuarioPostDTO nao pode ser nulo");
        }
        if (usuarioPostDTO.getPessoaId() == null) {
            throw new RuntimeException("ID da Pessoa e obrigatorio");
        }

        Pessoa pessoa = pessoaRepository.findById(usuarioPostDTO.getPessoaId())
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + usuarioPostDTO.getPessoaId() + " nao encontrada"));

        Usuario usuario = UsuarioMapper.toUsuarioFromPostDTO(usuarioPostDTO, pessoa);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(salvo, isAdmin(salvo.getId()));
    }

    @Override
    public UsuarioResponseDTO buscarUsuarioPorId(Long usuarioId) {
        if (usuarioId == null) {
            throw new RuntimeException("ID do usuario nao pode ser nulo");
        }
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario com ID " + usuarioId + " nao encontrado"));
        return UsuarioMapper.toUsuarioResponseDTO(usuario, isAdmin(usuario.getId()));
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(Long id, UsuarioPutDTO dto) {
        if (id == null) {
            throw new RuntimeException("ID do usuario nao pode ser nulo");
        }
        if (dto == null) {
            throw new RuntimeException("Dados nao podem ser nulos");
        }
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario com ID " + id + " nao encontrado"));

        if (dto.getEmail() != null) usuario.setEmail(dto.getEmail());
        if (dto.getSenha() != null) usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        if (dto.getFoto() != null) usuario.setFoto(dto.getFoto());
        if (dto.getBloqueioPublicacao() != null) usuario.setBloqueioPublicacao(dto.getBloqueioPublicacao());
        if (dto.getBloqueioChat() != null) usuario.setBloqueioChat(dto.getBloqueioChat());
        if (dto.getWhatsapp() != null && usuario.getPessoa() != null) {
            usuario.getPessoa().setWhatsapp(dto.getWhatsapp());
            pessoaRepository.save(usuario.getPessoa());
        }

        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(salvo, isAdmin(salvo.getId()));
    }

    @Override
    public void deletarUsuario(Long id) {
        if (id == null) {
            throw new RuntimeException("ID do usuario nao pode ser nulo");
        }
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario com ID " + id + " nao encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public UsuarioResponseDTO buscarPorEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new RuntimeException("Email e obrigatorio");
        }
        Usuario usuario = usuarioRepository.findByEmailIgnoreCase(email.trim())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario nao encontrado"));
        return UsuarioMapper.toUsuarioResponseDTO(usuario, isAdmin(usuario.getId()));
    }

    @Override
    public UsuarioResponseDTO autenticar(String emailOuCpf, String senha) {
        if (emailOuCpf == null || senha == null) {
            throw new RuntimeException("Email/CPF e senha sao obrigatorios");
        }
        String valor = emailOuCpf.trim();
        Usuario usuario;
        if (valor.contains("@")) {
            usuario = usuarioRepository.findByEmailIgnoreCase(valor)
                    .orElseThrow(() -> new RuntimeException("Credenciais invalidas"));
        } else {
            Pessoa pessoa = pessoaRepository.findByCpf(valor)
                    .orElseThrow(() -> new RuntimeException("Credenciais invalidas"));
            usuario = usuarioRepository.findById(pessoa.getId())
                    .orElseThrow(() -> new RuntimeException("Credenciais invalidas"));
        }

        if (!passwordEncoder.matches(senha, usuario.getSenha())) {
            throw new RuntimeException("Credenciais invalidas");
        }
        return UsuarioMapper.toUsuarioResponseDTO(usuario, isAdmin(usuario.getId()));
    }

    @Override
    public UsuarioResponseDTO registrar(RegistroDTO dto) {
        if (dto == null) {
            throw new RuntimeException("Dados de registro nao podem ser nulos");
        }
        if (pessoaRepository.existsByCpf(dto.getCpf())) {
            throw new RuntimeException("CPF ja cadastrado");
        }
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setCpf(dto.getCpf());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setWhatsapp(dto.getWhatsapp());
        Pessoa pessoaSalva = pessoaRepository.save(pessoa);

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setFoto(dto.getFoto());
        usuario.setBloqueioPublicacao(false);
        usuario.setBloqueioChat(false);
        usuario.setPessoa(pessoaSalva);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(salvo, false);
    }

    @Override
    public List<UsuarioResponseDTO> listarBloqueados() {
        return usuarioRepository.findByBloqueioPublicacaoTrueOrBloqueioChatTrue().stream()
                .map(u -> UsuarioMapper.toUsuarioResponseDTO(u, isAdmin(u.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO aplicarPunicao(Long id, Boolean bloqueioPublicacao, Boolean bloqueioChat) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario com ID " + id + " nao encontrado"));
        if (bloqueioPublicacao != null) usuario.setBloqueioPublicacao(bloqueioPublicacao);
        if (bloqueioChat != null) usuario.setBloqueioChat(bloqueioChat);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(salvo, isAdmin(salvo.getId()));
    }

    @Override
    public UsuarioResponseDTO removerPunicao(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario com ID " + id + " nao encontrado"));
        usuario.setBloqueioPublicacao(false);
        usuario.setBloqueioChat(false);
        Usuario salvo = usuarioRepository.save(usuario);
        return UsuarioMapper.toUsuarioResponseDTO(salvo, isAdmin(salvo.getId()));
    }

    @Override
    @Transactional
    public void excluirContaComAnuncios(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario com ID " + id + " nao encontrado");
        }
        anuncioRepository.deleteByUsuarioId(id);
        usuarioRepository.deleteById(id);
    }

    @Override
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        if (usuarioId == null || senhaAtual == null || novaSenha == null) {
            throw new RuntimeException("Dados obrigatorios nao fornecidos");
        }
        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
        if (!passwordEncoder.matches(senhaAtual, usuario.getSenha())) {
            throw new RuntimeException("Senha atual incorreta");
        }
        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);
    }
}