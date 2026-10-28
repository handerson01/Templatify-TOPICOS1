package br.unitins.tp1.repository;

import java.util.List;

import br.unitins.tp1.model.Imagem;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ImagemRepository implements PanacheRepository<Imagem> {
    public List<Imagem> findByTemplate(Long idTemplate) {
        return find("template.id", idTemplate).list();
    }
}