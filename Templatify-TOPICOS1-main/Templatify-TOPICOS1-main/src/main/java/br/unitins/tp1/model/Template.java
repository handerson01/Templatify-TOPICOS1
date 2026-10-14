package br.unitins.tp1.model;

import jakarta.persistence.Entity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Template extends DefaultEntity {

    private String nome;
    private String descricao;
    @NotNull
    @Column(nullable = false)
    private Boolean ativo = true;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria", nullable = false)
    private Categoria categoria;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_autor", nullable = false)
    private Criador autor;

    @OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Imagem> imagens = new ArrayList<>();

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_formato", nullable = false)
    private Formato formato;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_precificacao", nullable = false)
    private Precificacao precificacao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = Objects.requireNonNull(ativo, "ativo não pode ser nulo"); }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    public Criador getAutor() { return autor; }
    public void setAutor(Criador autor) { this.autor = autor; }

    public List<Imagem> getImagens() { return imagens; }
    public void setImagens(List<Imagem> imagens) { this.imagens = imagens; }

    public Formato getFormato() { return formato; }
    public void setFormato(Formato formato) { this.formato = formato; }

    public Precificacao getPrecificacao() { return precificacao; }
    public void setPrecificacao(Precificacao precificacao) { this.precificacao = precificacao; }
}