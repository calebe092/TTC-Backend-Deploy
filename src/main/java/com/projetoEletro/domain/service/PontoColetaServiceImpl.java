package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.PontoColetaPostDTO;
import com.projetoEletro.api.dto.put.PontoColetaPutDTO;
import com.projetoEletro.api.dto.response.PontoColetaResponseDTO;
import com.projetoEletro.api.mapper.PontoColetaMapper;
import com.projetoEletro.domain.model.PontoColeta;
import com.projetoEletro.domain.repository.PontoColetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PontoColetaServiceImpl  implements PontoColetaService {

    @Autowired
    private PontoColetaRepository pontoColetaRepository;

    public List<PontoColetaResponseDTO> listarPontoColeta() {
        return PontoColetaMapper.listPontoColetaResponseDTO(pontoColetaRepository.findAll());
    }

    public PontoColetaResponseDTO salvarPontocoleta(PontoColetaPostDTO pontoColetaPostDTO) {
        return PontoColetaMapper.toPontoColetaResponseDTO(pontoColetaRepository.save(PontoColetaMapper
                .toPontoColetaPostDTO(pontoColetaPostDTO)));
    }

    public PontoColetaResponseDTO buscarpontoColeta(Long pontoColetaId) {
        if (pontoColetaId == null) {
            throw new RuntimeException("ID do Ponto de Coleta não pode ser nulo");
        }

        return PontoColetaMapper.toBuscarPontoColetaDTO(
                pontoColetaRepository.findById(pontoColetaId)
                        .orElseThrow(() -> new RuntimeException("Ponto de Coleta com ID " + pontoColetaId + " não existe em nosso sistema")));
    }

    public PontoColetaResponseDTO atualizarPontoColeta(Long pontoColetaId, PontoColetaPutDTO pontoColetaPutDTO) {
        PontoColeta pontoColetaDB = buscarPontoColetaPorId(pontoColetaId);
        pontoColetaDB.setNome(pontoColetaPutDTO.getNome());
        pontoColetaDB.setLatitude(pontoColetaPutDTO.getLatitude());
        pontoColetaDB.setLongitude(pontoColetaPutDTO.getLongitude());
        pontoColetaDB.setEndereco(pontoColetaPutDTO.getEndereco());
        pontoColetaDB.setHorario(pontoColetaPutDTO.getHorario());

        return PontoColetaMapper.toPontoColetaResponseDTO(pontoColetaRepository.save(pontoColetaDB));
    }

    private PontoColeta buscarPontoColetaPorId(Long pontoColetaId) {
        if (pontoColetaId == null) {
            throw new RuntimeException("ID do Ponto de Coleta não Pode ser nulo");
        }

        return pontoColetaRepository.findById(pontoColetaId).orElseThrow(
                () -> new RuntimeException("Ponto de Coleta Com ID " + pontoColetaId + " não existe em nosso sistema"));

    }

    public void deletarPontoColeta(Long id) {
        buscarPontoColetaPorId(id);
        pontoColetaRepository.deleteById(id);

    }

}
