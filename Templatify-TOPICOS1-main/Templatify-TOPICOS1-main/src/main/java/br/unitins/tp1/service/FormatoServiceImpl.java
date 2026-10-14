package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.FormatoDTO;
import br.unitins.tp1.model.Formato;
import br.unitins.tp1.repository.FormatoRepository;
import br.unitins.tp1.repository.TemplateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class FormatoServiceImpl implements FormatoService {

    @Inject FormatoRepository repository;
    @Inject TemplateRepository templateRepository;

    @Override
    @Transactional
    public Formato create(FormatoDTO dto) {
        Formato entity = new Formato();
        map(dto, entity);
        repository.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Formato update(Long id, FormatoDTO dto) {
        Formato entity = findById(id);
        map(dto, entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Formato entity = findById(id);
        if (templateRepository.count("formato.id", id) > 0) {
            throw new BadRequestException("Formato está vinculado a um ou mais templates.");
        }
        repository.delete(entity);
    }

    @Override
    public Formato findById(Long id) {
        Formato entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Formato não encontrado.");
        return entity;
    }

    @Override
    public List<Formato> findAll() { return repository.listAll(); }

    @Override
    public List<Formato> findByNome(String nome) { return repository.findByNome(nome); }

    private void map(FormatoDTO dto, Formato entity) {
        entity.setNome(dto.nome());
        entity.setExtensao(dto.extensao());
    }
}