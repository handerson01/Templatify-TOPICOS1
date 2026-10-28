package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.ImagemDTO;
import br.unitins.tp1.dto.ImagemResponseDTO;
import br.unitins.tp1.model.Imagem;
import br.unitins.tp1.model.Template;
import br.unitins.tp1.repository.ImagemRepository;
import br.unitins.tp1.repository.TemplateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class ImagemServiceImpl implements ImagemService {

    @Inject ImagemRepository repository;
    @Inject TemplateRepository templateRepository;

    @Override
    @Transactional
    public ImagemResponseDTO create(ImagemDTO dto) {
        Imagem entity = new Imagem();
        map(dto, entity);
        repository.persist(entity);
        return ImagemResponseDTO.fromEntity(entity);
    }

    @Override
    @Transactional
    public ImagemResponseDTO update(Long id, ImagemDTO dto) {
        Imagem entity = findEntity(id);
        map(dto, entity);
        return ImagemResponseDTO.fromEntity(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) { repository.delete(findEntity(id)); }

    @Override
    public ImagemResponseDTO findById(Long id) { return ImagemResponseDTO.fromEntity(findEntity(id)); }

    @Override
    public List<ImagemResponseDTO> findAll() {
        return repository.listAll().stream().map(ImagemResponseDTO::fromEntity).toList();
    }

    @Override
    public List<ImagemResponseDTO> findByTemplate(Long idTemplate) {
        return repository.findByTemplate(idTemplate).stream().map(ImagemResponseDTO::fromEntity).toList();
    }

    private Imagem findEntity(Long id) {
        Imagem entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Imagem não encontrada.");
        return entity;
    }

    private void map(ImagemDTO dto, Imagem entity) {
        Template template = templateRepository.findById(dto.idTemplate());
        if (template == null) throw new NotFoundException("Template informado não existe.");
        entity.setUrl(dto.url());
        entity.setDescricao(dto.descricao());
        entity.setTemplate(template);
    }
}