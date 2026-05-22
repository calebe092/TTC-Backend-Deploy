package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.ConteudoEducativoPostDTO;
import com.projetoEletro.api.dto.put.ConteudoEducativoPutDTO;
import com.projetoEletro.api.dto.response.ConteudoEducativoResponseDTO;
import com.projetoEletro.api.mapper.ConteudoEducativoMapper;
import com.projetoEletro.domain.model.ConteudoEducativo;
import com.projetoEletro.domain.repository.ConteudoEducativoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteudoEducativoService {

    @Autowired
    private ConteudoEducativoRepository conteudoEducativoRepository;

    public List<ConteudoEducativoResponseDTO> listarConteudoEducativo(){
        return ConteudoEducativoMapper.listConteudoEducativoResponseDTO(conteudoEducativoRepository.findAll());
    }

    public ConteudoEducativoResponseDTO salvarConteudoEducativo(ConteudoEducativoPostDTO conteudoEducativoPostDTO){
        return ConteudoEducativoMapper.toConteudoEducativoResponseDTO(conteudoEducativoRepository.save(ConteudoEducativoMapper
                .toConteudoEducativoPostDTO(conteudoEducativoPostDTO)));
    }

    public ConteudoEducativoResponseDTO buscarConteudoEducativoId(Long conteudoEducativoId){

        if (conteudoEducativoId == null){
            throw new RuntimeException("ID do Conteudo educacional nao pode ser nulo");
        }

        return ConteudoEducativoMapper.toBuscarConteudoEducativoDTO(
                conteudoEducativoRepository.findById(conteudoEducativoId)
                        .orElseThrow(() -> new RuntimeException("Conteudo Educativo com ID" + conteudoEducativoId + " não existe em nossos sistemas")));

    }

    public ConteudoEducativoResponseDTO atualizarConteudoEducativo(Long conteudoEducativoId, ConteudoEducativoPutDTO conteudoEducativoPutDTO){
        ConteudoEducativo conteudoEducativoDB = buscarConteudoEducativoPorId(conteudoEducativoId);
        conteudoEducativoDB.setId(conteudoEducativoPutDTO.getId());
        conteudoEducativoDB.setTitulo(conteudoEducativoPutDTO.getTitulo());
        conteudoEducativoDB.setCategoria(conteudoEducativoPutDTO.getCategoria());
        conteudoEducativoDB.setTexto(conteudoEducativoPutDTO.getTexto());
        conteudoEducativoDB.setLinkVideo(conteudoEducativoPutDTO.getLinkVideo());
        conteudoEducativoDB.setAtivo(conteudoEducativoPutDTO.getAtivo());
        conteudoEducativoDB.setDataCriacao(conteudoEducativoPutDTO.getDataCriacao());

        return ConteudoEducativoMapper.toConteudoEducativoResponseDTO(conteudoEducativoRepository.save(conteudoEducativoDB));
    }


    private ConteudoEducativo buscarConteudoEducativoPorId(Long conteudoEducativoId){
        if (conteudoEducativoId == null){
            throw new RuntimeException("ID do Conteudo Educativo não Pode ser nulo");
        }

        return conteudoEducativoRepository.findById(conteudoEducativoId).orElseThrow(
                ()-> new RuntimeException("Conteudo Educativo Com ID " + conteudoEducativoId + " não existe em nosso sistema"));

    }

    public void deletarConteudoEducativo(Long id){
        buscarConteudoEducativoPorId(id);
        conteudoEducativoRepository.deleteById(id);
    }

}
