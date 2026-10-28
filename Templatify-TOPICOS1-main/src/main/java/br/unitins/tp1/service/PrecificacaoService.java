package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.PrecificacaoDTO;
import br.unitins.tp1.model.Precificacao;

public interface PrecificacaoService {
    Precificacao create(PrecificacaoDTO dto);
    Precificacao update(Long id, PrecificacaoDTO dto);
    void delete(Long id);
    Precificacao findById(Long id);
    List<Precificacao> findAll();
}