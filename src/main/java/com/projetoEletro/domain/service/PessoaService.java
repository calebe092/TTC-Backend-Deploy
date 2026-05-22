package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.PessoaPostDTO;
import com.projetoEletro.api.dto.put.PessoaPutDTO;
import com.projetoEletro.api.dto.response.PessoaResponseDTO;
import com.projetoEletro.api.mapper.PessoaMapper;
import com.projetoEletro.domain.model.Pessoa;
import com.projetoEletro.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;


    public List<PessoaResponseDTO> listarPessoa() {
        return PessoaMapper.listPessoaResponseDTO(pessoaRepository.findAll());

    }

    public PessoaResponseDTO salvarPessoa(PessoaPostDTO pessoaPostDTO){
        return PessoaMapper.toPessoaResponseDTO(pessoaRepository.save(PessoaMapper
                .toPessoaPostDTO(pessoaPostDTO)));
    }

    public PessoaResponseDTO buscarPessoaId(Long pessoaId){

        if (pessoaId == null){
            throw new RuntimeException("ID da pessoa não pode ser nulo");
        }

        return PessoaMapper.toBuscarPessoaResponseDTO(
                pessoaRepository.findById(pessoaId)
                        .orElseThrow(() -> new RuntimeException(" Grupo com ID " + pessoaId + " não existe em nosso sistema")));
    }

    public PessoaResponseDTO atualizarGrupoPorId(Long pessoaId, PessoaPutDTO pessoaPutDTO){
        Pessoa pessoaDB = buscarPessoaPorId(pessoaId);
        pessoaDB.setId(pessoaPutDTO.getId());
        pessoaDB.setNome(pessoaPutDTO.getNome());
        pessoaDB.setCpf(pessoaPutDTO.getCpf());
        pessoaDB.setDataNascimento(pessoaPutDTO.getDataNascimento());
        pessoaDB.setWhatsapp(pessoaPutDTO.getWhatsapp());

        return PessoaMapper.toPessoaResponseDTO(pessoaRepository.save(pessoaDB));
    }

    private Pessoa buscarPessoaPorId(Long pessoaId){
        if (pessoaId == null){
            throw new RuntimeException("ID do grupo não pode ser nulo");
        }

        return pessoaRepository.findById(pessoaId)
                .orElseThrow(()-> new RuntimeException("Grupo com ID " + pessoaId + " não existe em nosso sistema"));
    }



    public void deletarPessoa(Long id){
        buscarPessoaPorId(id);
        pessoaRepository.deleteById(id);
    }

}
