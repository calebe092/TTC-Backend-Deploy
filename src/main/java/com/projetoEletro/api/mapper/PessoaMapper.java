package com.projetoEletro.api.mapper;

import com.projetoEletro.api.dto.post.PessoaPostDTO;
import com.projetoEletro.api.dto.response.PessoaResponseDTO;
import com.projetoEletro.domain.model.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class PessoaMapper {

    public static List<PessoaResponseDTO> listPessoaResponseDTO(List<Pessoa> pessoas) {
        List<PessoaResponseDTO> list = new ArrayList<>();
        for (Pessoa pessoa : pessoas) {
            list.add(PessoaResponseDTO.builder().id(pessoa.getId())
                    .nome(pessoa.getNome())
                    .cpf(pessoa.getCpf())
                    .dataNascimento(pessoa.getDataNascimento())
                    .whatsapp(pessoa.getWhatsapp())
                    .build());
        }
        return list;
    }

    public static Pessoa toPessoaPostDTO(PessoaPostDTO pessoaPostDTO){
        return Pessoa.builder().id(pessoaPostDTO.getId())
                .nome(pessoaPostDTO.getNome())
                .cpf(pessoaPostDTO.getCpf())
                .dataNascimento(pessoaPostDTO.getDataNascimento())
                .whatsapp(pessoaPostDTO.getWhatsapp())
                .build();
    }

    public static PessoaResponseDTO toPessoaResponseDTO(Pessoa pessoa){
        return PessoaResponseDTO.builder().id(pessoa.getId())
                .nome(pessoa.getNome())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .whatsapp(pessoa.getWhatsapp())
                .build();
    }

    public static PessoaResponseDTO toBuscarPessoaResponseDTO(Pessoa pessoa){
        return PessoaResponseDTO.builder().id(pessoa.getId())
                .nome(pessoa.getNome()).cpf(pessoa.getCpf())
                .cpf(pessoa.getCpf())
                .dataNascimento(pessoa.getDataNascimento())
                .whatsapp(pessoa.getWhatsapp())
                .build();
    }
}
