package br.unitins.tp1.repository;

import java.util.List;

import br.unitins.tp1.model.Formato;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FormatoRepository implements PanacheRepository<Formato> {
    public List<Formato> findByNome(String nome) {
        return find("upper(nome) LIKE upper(?1)", "%" + nome + "%").list();
    }
}