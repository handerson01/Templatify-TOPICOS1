
package br.unitins.tp1.repository;

import java.math.BigDecimal;
import java.util.List;

import br.unitins.tp1.model.Template;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TemplateRepository implements PanacheRepository<Template> {

    public List<Template> findByNome(String nome) {
        return find("upper(nome) LIKE upper(?1)", "%" + nome + "%").list();
    }

    public List<Template> findByDescricao(String descricao) {
        return find("upper(descricao) LIKE upper(?1)", "%" + descricao + "%").list();
    }

    public List<Template> findByCategoria(String categoria) {
        return find("upper(categoria.nome) LIKE upper(?1)", "%" + categoria + "%").list();
    }

    public List<Template> findByFormato(String formato) {
        return find("upper(formato.nome) LIKE upper(?1)", "%" + formato + "%").list();
    }

    public List<Template> findByAutor(String autor) {
        return find("upper(autor.nome) LIKE upper(?1)", "%" + autor + "%").list();
    }

    public List<Template> findByPreco(BigDecimal preco) {
        return find("precificacao.valorBase = ?1", preco).list();
    }

    public List<Template> findByImagemUrl(String imagemUrl) {
        return find("select distinct t from Template t join t.imagens imagem " +
                "where upper(imagem.url) LIKE upper(?1)", "%" + imagemUrl + "%").list();
    }

}