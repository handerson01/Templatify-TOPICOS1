package br.unitins.tp1.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Precificacao extends DefaultEntity {

    @Column(precision = 19, scale = 2)
    private BigDecimal valorBase;
    private String moeda;
    @Column(precision = 5, scale = 2)
    private BigDecimal percentualDesconto;
    private LocalDateTime inicioPromocao;
    private LocalDateTime fimPromocao;

    public BigDecimal getValorBase() { return valorBase; }
    public void setValorBase(BigDecimal valorBase) { this.valorBase = valorBase; }

    public String getMoeda() { return moeda; }
    public void setMoeda(String moeda) { this.moeda = moeda; }

    public BigDecimal getPercentualDesconto() { return percentualDesconto; }
    public void setPercentualDesconto(BigDecimal percentualDesconto) { this.percentualDesconto = percentualDesconto; }

    public LocalDateTime getInicioPromocao() { return inicioPromocao; }
    public void setInicioPromocao(LocalDateTime inicioPromocao) { this.inicioPromocao = inicioPromocao; }

    public LocalDateTime getFimPromocao() { return fimPromocao; }
    public void setFimPromocao(LocalDateTime fimPromocao) { this.fimPromocao = fimPromocao; }
}