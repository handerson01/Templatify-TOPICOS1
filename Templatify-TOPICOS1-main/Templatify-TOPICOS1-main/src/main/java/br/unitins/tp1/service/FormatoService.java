package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.FormatoDTO;
import br.unitins.tp1.model.Formato;

public interface FormatoService {
    Formato create(FormatoDTO dto);
    Formato update(Long id, FormatoDTO dto);
    void delete(Long id);
    Formato findById(Long id);
    List<Formato> findAll();
    List<Formato> findByNome(String nome);
}