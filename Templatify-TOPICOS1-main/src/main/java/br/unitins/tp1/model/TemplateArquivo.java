package br.unitins.tp1.model;

import jakarta.persistence.Entity;

@Entity
public class TemplateArquivo extends Template {

    private String arquivoUrl;
    private Long tamanhoBytes;
    private String checksum;

    public String getArquivoUrl() { return arquivoUrl; }
    public void setArquivoUrl(String arquivoUrl) { this.arquivoUrl = arquivoUrl; }

    public Long getTamanhoBytes() { return tamanhoBytes; }
    public void setTamanhoBytes(Long tamanhoBytes) { this.tamanhoBytes = tamanhoBytes; }

    public String getChecksum() { return checksum; }
    public void setChecksum(String checksum) { this.checksum = checksum; }
}