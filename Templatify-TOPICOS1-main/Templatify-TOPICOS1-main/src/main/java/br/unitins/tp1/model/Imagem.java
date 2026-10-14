package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Imagem extends DefaultEntity {

    private String url;
    private String descricao;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_template", nullable = false)
    private Template template;

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Template getTemplate() { return template; }
    public void setTemplate(Template template) { this.template = template; }
}