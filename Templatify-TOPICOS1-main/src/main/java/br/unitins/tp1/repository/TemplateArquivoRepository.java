package br.unitins.tp1.repository;

import java.util.List;
import br.unitins.tp1.model.TemplateArquivo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemplateArquivoRepository implements PanacheRepository<TemplateArquivo> {

    public List<TemplateArquivo> findByNome(String nome) {
        return find("UPPER(nome) LIKE UPPER(?1)", "%" + nome + "%").list();
    }
}
