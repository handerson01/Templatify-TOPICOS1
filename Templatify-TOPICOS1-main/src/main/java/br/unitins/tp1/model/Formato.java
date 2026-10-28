package br.unitins.tp1.model;

import jakarta.persistence.Entity;

@Entity
public class Formato extends DefaultEntity {

    private String nome;
    private String extensao;

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getExtensao() { return extensao; }
    public void setExtensao(String extensao) { this.extensao = extensao; }
}