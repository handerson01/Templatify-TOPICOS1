package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.TemplateNuvemDTO;
import br.unitins.tp1.dto.TemplateNuvemResponseDTO;
import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.model.Criador;
import br.unitins.tp1.model.Formato;
import br.unitins.tp1.model.Precificacao;
import br.unitins.tp1.model.TemplateNuvem;
import br.unitins.tp1.repository.CategoriaRepository;
import br.unitins.tp1.repository.CriadorRepository;
import br.unitins.tp1.repository.FormatoRepository;
import br.unitins.tp1.repository.PrecificacaoRepository;
import br.unitins.tp1.repository.TemplateNuvemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TemplateNuvemServiceImpl implements TemplateNuvemService {

    @Inject TemplateNuvemRepository repository;
    @Inject CategoriaRepository categoriaRepository;
    @Inject CriadorRepository criadorRepository;
    @Inject FormatoRepository formatoRepository;
    @Inject PrecificacaoRepository precificacaoRepository;

    @Override
    @Transactional
    public TemplateNuvemResponseDTO create(TemplateNuvemDTO dto) {
        TemplateNuvem entity = new TemplateNuvem();
        map(dto, entity);
        repository.persist(entity);
        return TemplateNuvemResponseDTO.fromEntity(entity);
    }

    @Override
    @Transactional
    public TemplateNuvemResponseDTO update(Long id, TemplateNuvemDTO dto) {
        TemplateNuvem entity = findEntity(id);
        map(dto, entity);
        return TemplateNuvemResponseDTO.fromEntity(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) { repository.delete(findEntity(id)); }

    @Override
    public TemplateNuvemResponseDTO findById(Long id) {
        return TemplateNuvemResponseDTO.fromEntity(findEntity(id));
    }

    @Override
    public List<TemplateNuvemResponseDTO> findAll() {
        return repository.listAll().stream().map(TemplateNuvemResponseDTO::fromEntity).toList();
    }

    @Override
    public List<TemplateNuvemResponseDTO> findByNome(String nome) {
        return repository.findByNome(nome).stream().map(TemplateNuvemResponseDTO::fromEntity).toList();
    }

    private TemplateNuvem findEntity(Long id) {
        TemplateNuvem entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Template na nuvem não encontrado.");
        return entity;
    }

    private void map(TemplateNuvemDTO dto, TemplateNuvem entity) {
        Categoria categoria = categoriaRepository.findById(dto.idCategoria());
        if (categoria == null) throw new NotFoundException("Categoria informada não existe.");
        if (!Boolean.TRUE.equals(categoria.getAtiva())) {
            throw new BadRequestException("Não é permitido vincular template a uma categoria inativa.");
        }
        Criador autor = criadorRepository.findById(dto.idAutor());
        if (autor == null) throw new NotFoundException("Autor informado não existe.");
        Formato formato = formatoRepository.findById(dto.idFormato());
        if (formato == null) throw new NotFoundException("Formato informado não existe.");
        Precificacao precificacao = precificacaoRepository.findById(dto.idPrecificacao());
        if (precificacao == null) throw new NotFoundException("Precificação informada não existe.");

        entity.setNome(dto.nome());
        entity.setDescricao(dto.descricao());
        entity.setCategoria(categoria);
        entity.setAutor(autor);
        entity.setFormato(formato);
        entity.setPrecificacao(precificacao);
        entity.setAtivo(dto.ativo() != null ? dto.ativo() : true);
        entity.setUrlAcesso(dto.urlAcesso());
        entity.setPlataforma(dto.plataforma());
        entity.setRequerContaPaga(dto.requerContaPaga());
    }
}