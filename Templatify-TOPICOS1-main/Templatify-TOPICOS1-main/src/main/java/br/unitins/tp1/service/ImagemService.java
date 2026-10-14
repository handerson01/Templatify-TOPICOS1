package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.ImagemDTO;
import br.unitins.tp1.dto.ImagemResponseDTO;

public interface ImagemService {
    ImagemResponseDTO create(ImagemDTO dto);
    ImagemResponseDTO update(Long id, ImagemDTO dto);
    void delete(Long id);
    ImagemResponseDTO findById(Long id);
    List<ImagemResponseDTO> findAll();
    List<ImagemResponseDTO> findByTemplate(Long idTemplate);
}