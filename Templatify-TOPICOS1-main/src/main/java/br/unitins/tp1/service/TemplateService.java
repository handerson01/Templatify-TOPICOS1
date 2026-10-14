package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.model.Template;

public interface TemplateService {
    Template create(Template template);
    void update(Long id, Template template);
    void delete(Long id);
    Template findById(Long id);
    List<Template> findByNome(String nome);
    List<Template> findByDescricao(String descricao);
    List<Template> findByCategoria(String categoria);
    List<Template> findByFormato(String formato);
    List<Template> findByAutor(String autor);
    List<Template> findByPreco(Double preco);
    List<Template> findByImagemUrl(String imagemUrl);
    List<Template> findAll();
}
