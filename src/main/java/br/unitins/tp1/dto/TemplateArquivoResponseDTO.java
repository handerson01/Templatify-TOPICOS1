package br.unitins.tp1.dto;

import br.unitins.tp1.model.Categoria;
import br.unitins.tp1.model.Criador;
import br.unitins.tp1.model.TemplateArquivo;

public record TemplateArquivoResponseDTO(
    long id,
    String nome,
    String descricao,
    Double preco,
    Categoria categoria,
    String formato,
    Criador autor,
    String imagemUrl,
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
            entity.getPreco(),
            entity.getCategoria(),
            entity.getFormato(),
            entity.getAutor(),
            entity.getImagemUrl(),
            entity.getAtivo(),
            entity.getArquivoUrl(),
            entity.getTamanhoBytes(),
            entity.getChecksum()
        );
    }
}