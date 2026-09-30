package br.unitins.tp1.service;

import java.util.List;
import br.unitins.tp1.dto.TemplateArquivoDTO;
import br.unitins.tp1.dto.TemplateArquivoResponseDTO;
import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.model.Criador;
import br.unitins.tp1.model.TemplateArquivo;
import br.unitins.tp1.repository.CategoriaRepository;
import br.unitins.tp1.repository.CriadorRepository;
import br.unitins.tp1.repository.TemplateArquivoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TemplateArquivoServiceImpl implements TemplateArquivoService {

    @Inject
    TemplateArquivoRepository repository;

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    CriadorRepository criadorRepository;

    @Override
    @Transactional
    public TemplateArquivoResponseDTO create(TemplateArquivoDTO dto) {
        TemplateArquivo entity = new TemplateArquivo();
        mapDtoToEntity(dto, entity);

        repository.persist(entity);
        return TemplateArquivoResponseDTO.fromEntity(entity);
    }

    @Override
    @Transactional
    public TemplateArquivoResponseDTO update(Long id, TemplateArquivoDTO dto) {
        TemplateArquivo entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Template não encontrado.");
        }

        mapDtoToEntity(dto, entity);
        return TemplateArquivoResponseDTO.fromEntity(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) {
            throw new NotFoundException("Template não encontrado.");
        }
    }

    @Override
    public TemplateArquivoResponseDTO findById(Long id) {
        TemplateArquivo entity = repository.findById(id);
        if (entity == null) {
            throw new NotFoundException("Template não encontrado.");
        }
        return TemplateArquivoResponseDTO.fromEntity(entity);
    }

    @Override
    public List<TemplateArquivoResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome)
                .stream()
                .map(TemplateArquivoResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<TemplateArquivoResponseDTO> findAll() {
        return repository.listAll()
                .stream()
                .map(TemplateArquivoResponseDTO::fromEntity)
                .toList();
    }

    private void mapDtoToEntity(TemplateArquivoDTO dto, TemplateArquivo entity) {
        Categoria categoria = categoriaRepository.findById(dto.idCategoria());
        if (categoria == null) {
            throw new NotFoundException("Categoria informada não existe.");
        }

        Criador autor = criadorRepository.findById(dto.idAutor());
        if (autor == null) {
            throw new NotFoundException("Autor informado não existe.");
        }

        entity.setNome(dto.nome());
        entity.setDescricao(dto.descricao());
        entity.setPreco(dto.preco());
        entity.setFormato(dto.formato());
        entity.setImagemUrl(dto.imagemUrl());
        entity.setAtivo(dto.ativo() != null ? dto.ativo() : true);
        entity.setArquivoUrl(dto.arquivoUrl());
        entity.setTamanhoBytes(dto.tamanhoBytes());
        entity.setChecksum(dto.checksum());
        entity.setCategoria(categoria);
        entity.setAutor(autor);
    }
}