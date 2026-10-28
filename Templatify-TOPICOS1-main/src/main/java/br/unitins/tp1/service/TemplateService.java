package br.unitins.tp1.service;

import java.math.BigDecimal;
import java.util.List;

import br.unitins.tp1.dto.TemplateResponseDTO;

public interface TemplateService {
    TemplateResponseDTO findById(Long id);
    List<TemplateResponseDTO> findByNome(String nome);
    List<TemplateResponseDTO> findByDescricao(String descricao);
    List<TemplateResponseDTO> findByCategoria(String categoria);
    List<TemplateResponseDTO> findByFormato(String formato);
    List<TemplateResponseDTO> findByAutor(String autor);
    List<TemplateResponseDTO> findByPreco(BigDecimal preco);
    List<TemplateResponseDTO> findByImagemUrl(String imagemUrl);
    List<TemplateResponseDTO> findAll();
}
