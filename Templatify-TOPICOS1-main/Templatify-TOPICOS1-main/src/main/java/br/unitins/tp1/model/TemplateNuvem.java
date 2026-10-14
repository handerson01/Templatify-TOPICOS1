package br.unitins.tp1.model;

import jakarta.persistence.Entity;

@Entity
public class TemplateNuvem extends Template {

    private String urlAcesso;
    private String plataforma;
    private Boolean requerContaPaga;

    public String getUrlAcesso() { return urlAcesso; }
    public void setUrlAcesso(String urlAcesso) { this.urlAcesso = urlAcesso; }

    public String getPlataforma() { return plataforma; }
    public void setPlataforma(String plataforma) { this.plataforma = plataforma; }

    public Boolean getRequerContaPaga() { return requerContaPaga; }
    public void setRequerContaPaga(Boolean requerContaPaga) { this.requerContaPaga = requerContaPaga; }
}