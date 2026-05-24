package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.PessoaPostDTO;
import com.projetoEletro.api.dto.put.PessoaPutDTO;
import com.projetoEletro.api.dto.response.PessoaResponseDTO;
import com.projetoEletro.api.mapper.PessoaMapper;
import com.projetoEletro.domain.model.Pessoa;
import com.projetoEletro.domain.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<PessoaResponseDTO> listarPessoa() {
        return PessoaMapper.listPessoaResponseDTO(pessoaRepository.findAll());
    }

    public PessoaResponseDTO salvarPessoa(PessoaPostDTO pessoaPostDTO) {
        validarPessoa(pessoaPostDTO.getCpf(), pessoaPostDTO.getDataNascimento(), null);
        return PessoaMapper.toPessoaResponseDTO(pessoaRepository.save(PessoaMapper
                .toPessoaPostDTO(pessoaPostDTO)));
    }

    public PessoaResponseDTO buscarPessoaId(Long pessoaId) {
        if (pessoaId == null) {
            throw new RuntimeException("ID da pessoa não pode ser nulo");
        }
        return PessoaMapper.toBuscarPessoaResponseDTO(
                pessoaRepository.findById(pessoaId)
                        .orElseThrow(() -> new RuntimeException("Pessoa com ID " + pessoaId + " não existe em nosso sistema")));
    }

    public PessoaResponseDTO atualizarGrupoPorId(Long pessoaId, PessoaPutDTO pessoaPutDTO) {
        Pessoa pessoaDB = buscarPessoaPorId(pessoaId);
        validarPessoa(pessoaPutDTO.getCpf(), pessoaPutDTO.getDataNascimento(), pessoaId);
        pessoaDB.setNome(pessoaPutDTO.getNome());
        pessoaDB.setCpf(pessoaPutDTO.getCpf());
        pessoaDB.setDataNascimento(pessoaPutDTO.getDataNascimento());
        pessoaDB.setWhatsapp(pessoaPutDTO.getWhatsapp());

        return PessoaMapper.toPessoaResponseDTO(pessoaRepository.save(pessoaDB));
    }

    private Pessoa buscarPessoaPorId(Long pessoaId) {
        if (pessoaId == null) {
            throw new RuntimeException("ID da pessoa não pode ser nulo");
        }
        return pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new RuntimeException("Pessoa com ID " + pessoaId + " não existe em nosso sistema"));
    }

    public void deletarPessoa(Long id) {
        buscarPessoaPorId(id);
        pessoaRepository.deleteById(id);
    }

    private void validarPessoa(String cpf, LocalDate dataNascimento, Long pessoaIdAtual) {
        if (cpf == null || cpf.isBlank()) {
            throw new RuntimeException("CPF é obrigatório");
        }
        String cpfLimpo = cpf.replaceAll("\\D", "");
        if (!isCpfValido(cpfLimpo)) {
            throw new RuntimeException("CPF inválido");
        }
        if (pessoaRepository.existsByCpf(cpfLimpo)) {
            var existente = pessoaRepository.findByCpf(cpfLimpo).get();
            if (pessoaIdAtual == null || !pessoaIdAtual.equals(existente.getId())) {
                throw new RuntimeException("CPF já cadastrado");
            }
        }
        if (dataNascimento != null) {
            int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
            if (idade < 18) {
                throw new RuntimeException("É necessário ter 18 anos ou mais");
            }
        }
    }

    private boolean isCpfValido(String cpf) {
        if (cpf.length() != 11 || cpf.matches("(\\d)\\1{10}")) {
            return false;
        }
        int[] peso1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma = 0;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * peso1[i];
        }
        int dig1 = 11 - (soma % 11);
        if (dig1 > 9) dig1 = 0;
        if (dig1 != (cpf.charAt(9) - '0')) return false;

        soma = 0;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso2[i];
        }
        int dig2 = 11 - (soma % 11);
        if (dig2 > 9) dig2 = 0;
        return dig2 == (cpf.charAt(10) - '0');
    }
}
