package br.unitins.tp1.service;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.model.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria salvar(CategoriaDTO dto);

    List<Categoria> listarTodos();

    Categoria buscarPorId(Long id);

    Categoria atualizar(Long id, CategoriaDTO dto);

    Categoria inativar(Long id);

    void deletar(Long id);
}