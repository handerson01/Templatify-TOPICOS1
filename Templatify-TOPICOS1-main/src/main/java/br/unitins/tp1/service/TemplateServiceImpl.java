
package br.unitins.tp1.service;

import java.math.BigDecimal;
import java.util.List;

import br.unitins.tp1.dto.TemplateResponseDTO;
import br.unitins.tp1.model.Template;
import br.unitins.tp1.repository.TemplateRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TemplateServiceImpl implements TemplateService {

    @Inject
    TemplateRepository repository;

    @Override
    public TemplateResponseDTO findById(Long id) {
        Template template = repository.findById(id);
        if (template == null) throw new NotFoundException("Template não encontrado.");
        return TemplateResponseDTO.fromEntity(template);
    }

    @Override
    public List<TemplateResponseDTO> findByNome(String nome) {
        return map(repository.findByNome(nome));
    }

    @Override
    public List<TemplateResponseDTO> findByDescricao(String descricao) {
        return map(repository.findByDescricao(descricao));
    }

    @Override
    public List<TemplateResponseDTO> findByCategoria(String categoria) {
        return map(repository.findByCategoria(categoria));
    }

    @Override
    public List<TemplateResponseDTO> findByFormato(String formato) {
        return map(repository.findByFormato(formato));
    }

    @Override
    public List<TemplateResponseDTO> findByAutor(String autor) {
        return map(repository.findByAutor(autor));
    }

    @Override
    public List<TemplateResponseDTO> findByPreco(BigDecimal preco) {
        return map(repository.findByPreco(preco));
    }

    @Override
    public List<TemplateResponseDTO> findByImagemUrl(String imagemUrl) {
        return map(repository.findByImagemUrl(imagemUrl));
    }

    @Override
    public List<TemplateResponseDTO> findAll() {
        return map(repository.listAll());
    }

    private List<TemplateResponseDTO> map(List<Template> templates) {
        return templates.stream().map(TemplateResponseDTO::fromEntity).toList();
    }
}