package br.unitins.tp1.service;

import br.unitins.tp1.dto.CategoriaDTO;
import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.repository.CategoriaRepository;
import br.unitins.tp1.repository.TemplateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import br.unitins.tp1.exception.CategoriaEmUsoException;

import java.util.List;

@ApplicationScoped
public class CategoriaServiceImpl implements CategoriaService {

    @Inject
    CategoriaRepository repository;

    @Inject
    TemplateRepository templateRepository;

    @Override
    @Transactional
    public Categoria salvar(CategoriaDTO dto) {
        Categoria categoria = new Categoria();
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setAtiva(dto.ativa());
        repository.persist(categoria);
        return categoria;
    }

    @Override
    public List<Categoria> listarTodos() {
        return repository.listAll();
    }

    @Override
    public Categoria buscarPorId(Long id) {
        Categoria categoria = repository.findById(id);
        if (categoria == null) {
            throw new NotFoundException("Categoria não encontrada com o ID: " + id);
        }
        return categoria;
    }

    @Override
    @Transactional
    public Categoria atualizar(Long id, CategoriaDTO dto) {
        Categoria categoria = buscarPorId(id);
        categoria.setNome(dto.nome());
        categoria.setDescricao(dto.descricao());
        categoria.setAtiva(dto.ativa());
        return categoria;
    }

    @Override
    @Transactional
    public Categoria inativar(Long id) {
        Categoria categoria = buscarPorId(id);
        categoria.setAtiva(false);
        return categoria;
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        Categoria categoria = buscarPorId(id);
        if (templateRepository.count("categoria.id", id) > 0) {
            throw new CategoriaEmUsoException("Categoria vinculada a templates não pode ser excluída. Inative-a ou reatribua os templates.");
        }
        repository.delete(categoria);
    }
}