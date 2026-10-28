package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.TemplateNuvemDTO;
import br.unitins.tp1.dto.TemplateNuvemResponseDTO;

public interface TemplateNuvemService {
    TemplateNuvemResponseDTO create(TemplateNuvemDTO dto);
    TemplateNuvemResponseDTO update(Long id, TemplateNuvemDTO dto);
    void delete(Long id);
    TemplateNuvemResponseDTO findById(Long id);
    List<TemplateNuvemResponseDTO> findAll();
    List<TemplateNuvemResponseDTO> findByNome(String nome);
}