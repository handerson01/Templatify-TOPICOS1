package br.unitins.tp1.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

@Entity
public class Precificacao extends DefaultEntity {

    private Double valorBase;
    private String moeda;
    private Double percentualDesconto;
    private LocalDateTime inicioPromocao;
    private LocalDateTime fimPromocao;

    public Double getValorBase() { return valorBase; }
    public void setValorBase(Double valorBase) { this.valorBase = valorBase; }

    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }

    public Double getPercentualDesconto() { return percentualDesconto; }
    public void setPercentualDesconto(Double percentualDesconto) { this.percentualDesconto = percentualDesconto; }

    public LocalDateTime getInicioPromocao() { return inicioPromocao; }
    public void setInicioPromocao(LocalDateTime inicioPromocao) { this.inicioPromocao = inicioPromocao; }

    public LocalDateTime getFimPromocao() { return fimPromocao; }
    public void setFimPromocao(LocalDateTime fimPromocao) { this.fimPromocao = fimPromocao; }
}