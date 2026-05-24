package com.projetoEletro.domain.service;

import com.projetoEletro.api.dto.post.DenunciaPostDTO;
import com.projetoEletro.api.dto.put.DenunciaPutDTO;
import com.projetoEletro.api.dto.response.DenunciaResponseDTO;

import java.util.List;

public interface DenunciaService {
    List<DenunciaResponseDTO> listarDenuncias();
    DenunciaResponseDTO salvarDenuncia(DenunciaPostDTO denunciaPostDTO);
    DenunciaResponseDTO buscarDenunciaPorId(Long id);
    List<DenunciaResponseDTO> buscarDenunciasPorUsuario(Long usuarioId);
    DenunciaResponseDTO atualizarDenuncia(Long id, DenunciaPutDTO denunciaPutDTO);
    void deletarDenuncia(Long id);
    DenunciaResponseDTO resolver(Long id);
}
