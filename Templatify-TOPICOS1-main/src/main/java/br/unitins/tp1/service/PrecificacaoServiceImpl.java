package br.unitins.tp1.service;

import java.util.List;

import br.unitins.tp1.dto.PrecificacaoDTO;
import br.unitins.tp1.model.Precificacao;
import br.unitins.tp1.repository.PrecificacaoRepository;
import br.unitins.tp1.repository.TemplateRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PrecificacaoServiceImpl implements PrecificacaoService {

    @Inject PrecificacaoRepository repository;
    @Inject TemplateRepository templateRepository;

    @Override
    @Transactional
    public Precificacao create(PrecificacaoDTO dto) {
        Precificacao entity = new Precificacao();
        map(dto, entity);
        repository.persist(entity);
        return entity;
    }

    @Override
    @Transactional
    public Precificacao update(Long id, PrecificacaoDTO dto) {
        Precificacao entity = findById(id);
        map(dto, entity);
        return entity;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Precificacao entity = findById(id);
        if (templateRepository.count("precificacao.id", id) > 0) {
            throw new BadRequestException("Precificação está vinculada a um ou mais templates.");
        }
        repository.delete(entity);
    }

    @Override
    public Precificacao findById(Long id) {
        Precificacao entity = repository.findById(id);
        if (entity == null) throw new NotFoundException("Precificação não encontrada.");
        return entity;
    }

    @Override
    public List<Precificacao> findAll() { return repository.listAll(); }

    private void map(PrecificacaoDTO dto, Precificacao entity) {
        entity.setValorBase(dto.valorBase());
        entity.setMoeda(dto.moeda().toUpperCase());
        entity.setPercentualDesconto(dto.percentualDesconto());
        entity.setInicioPromocao(dto.inicioPromocao());
        entity.setFimPromocao(dto.fimPromocao());
    }
}