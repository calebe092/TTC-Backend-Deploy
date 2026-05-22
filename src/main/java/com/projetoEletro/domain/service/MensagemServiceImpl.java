package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.MensagemPostDTO;
import com.projetoEletro.api.dto.put.MensagemPutDTO;
import com.projetoEletro.api.dto.response.MensagemResponseDTO;
import com.projetoEletro.api.mapper.MensagemMapper;
import com.projetoEletro.domain.exception.MensagemNotFoundException;
import com.projetoEletro.domain.model.Mensagem;
import com.projetoEletro.domain.repository.MensagemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class MensagemServiceImpl implements MensagemService {

    private final MensagemRepository mensagemRepository;

    @Override
    public List<MensagemResponseDTO> listar() {
        return mensagemRepository.findAll()
                .stream()
                .map(MensagemMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MensagemResponseDTO buscarPorId(Long mensagemId) {
        Mensagem mensagem = mensagemRepository.findById(mensagemId)
                .orElseThrow(() -> new MensagemNotFoundException(mensagemId));
        return MensagemMapper.toResponseDTO(mensagem);
    }

    @Override
    public MensagemResponseDTO criar(MensagemPostDTO mensagemPostDTO) {
        Mensagem mensagem = MensagemMapper.toModel(mensagemPostDTO);
        mensagem.setDataCriacao(LocalDateTime.now());
        Mensagem salvo = mensagemRepository.save(mensagem);
        return MensagemMapper.toResponseDTO(salvo);
    }

    @Override
    public MensagemResponseDTO atualizar(Long mensagemId, MensagemPutDTO mensagemPutDTO) {
        Mensagem mensagem = mensagemRepository.findById(mensagemId)
                .orElseThrow(() -> new MensagemNotFoundException(mensagemId));

        MensagemMapper.updateEntity(mensagemPutDTO, mensagem);
        Mensagem atualizado = mensagemRepository.save(mensagem);
        return MensagemMapper.toResponseDTO(atualizado);
    }

    @Override
    public void deletar(Long mensagemId) {
        if (!mensagemRepository.existsById(mensagemId)) {
            throw new MensagemNotFoundException(mensagemId);
        }
        mensagemRepository.deleteById(mensagemId);
    }
}
