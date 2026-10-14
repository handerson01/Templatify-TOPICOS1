package br.unitins.tp1.dto;

import br.unitins.tp1.model.TemplateArquivo;

public record TemplateArquivoResponseDTO(
    long id,
    String nome,
    String descricao,
    Long idCategoria,
    Long idFormato,
    Long idPrecificacao,
    Long idAutor,
    Boolean ativo,
    String arquivoUrl,
    Long tamanhoBytes,
    String checksum
) {
    public static TemplateArquivoResponseDTO fromEntity(TemplateArquivo entity) {
        return new TemplateArquivoResponseDTO(
            entity.getId(),
            entity.getNome(),
            entity.getDescricao(),
            entity.getCategoria().getId(),
            entity.getFormato().getId(),
            entity.getPrecificacao().getId(),
            entity.getAutor().getId(),
            entity.getAtivo(),
            entity.getArquivoUrl(),
            entity.getTamanhoBytes(),
            entity.getChecksum()
        );
    }
}