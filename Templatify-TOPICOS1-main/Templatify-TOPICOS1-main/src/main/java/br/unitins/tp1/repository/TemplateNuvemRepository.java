package br.unitins.tp1.repository;

import java.util.List;

import br.unitins.tp1.model.TemplateNuvem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemplateNuvemRepository implements PanacheRepository<TemplateNuvem> {
    public List<TemplateNuvem> findByNome(String nome) {
        return find("upper(nome) LIKE upper(?1)", "%" + nome + "%").list();
    }
}