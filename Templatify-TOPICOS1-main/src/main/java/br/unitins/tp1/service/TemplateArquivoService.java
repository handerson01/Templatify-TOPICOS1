package br.unitins.tp1.service;

import java.util.List;
import br.unitins.tp1.dto.TemplateArquivoDTO;
import br.unitins.tp1.dto.TemplateArquivoResponseDTO;

public interface TemplateArquivoService {
    TemplateArquivoResponseDTO create(TemplateArquivoDTO dto);
    TemplateArquivoResponseDTO update(Long id, TemplateArquivoDTO dto);
    void delete(Long id);
    TemplateArquivoResponseDTO findById(Long id);
    List<TemplateArquivoResponseDTO> findByNome(String nome);
    List<TemplateArquivoResponseDTO> findAll();
}