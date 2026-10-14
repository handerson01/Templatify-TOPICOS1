
package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Template;
import br.unitins.tp1.repository.TemplateRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TemplateServiceImpl implements TemplateService {

    @Inject
    TemplateRepository repository;

    @Override
    @Transactional
    public Template create(Template template) {
        repository.persist(template);
        return template;
    }

    @Override
    @Transactional
    public void update(Long id, Template template) {

        Template templateBanco = repository.findById(id);

        if (templateBanco == null) {
            throw new RuntimeException("Template não encontrado");
        }

        templateBanco.setNome(template.getNome());
        templateBanco.setDescricao(template.getDescricao());
        templateBanco.setCategoria(template.getCategoria());
        templateBanco.setFormato(template.getFormato());
        templateBanco.setAutor(template.getAutor());
        templateBanco.setPreco(template.getPreco());
        templateBanco.setImagemUrl(template.getImagemUrl());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Template findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Template> findByNome(String nome) {
        return repository.findByNome(nome);
    }

    @Override
    public List<Template> findByDescricao(String descricao) {
        return repository.findByDescricao(descricao);
    }

    @Override
    public List<Template> findByCategoria(String categoria) {
        return repository.findByCategoria(categoria);
    }

    @Override
    public List<Template> findByFormato(String formato) {
        return repository.findByFormato(formato);
    }

    @Override
    public List<Template> findByAutor(String autor) {
        return repository.findByAutor(autor);
    }

    @Override
    public List<Template> findByPreco(Double preco) {
        return repository.findByPreco(preco);
    }

    @Override
    public List<Template> findByImagemUrl(String imagemUrl) {
        return repository.findByImagemUrl(imagemUrl);
    }

    @Override
    public List<Template> findAll() {
        return repository.listAll();
    }
}