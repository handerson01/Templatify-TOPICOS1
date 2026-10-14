package br.unitins.tp1.dto;

import br.unitins.tp1.model.Template;

public record TemplateResponseDTO(
        Long id,
        String nome,
        String descricao,
        Boolean ativo,
        Long idCategoria,
        Long idAutor,
        Long idFormato,
        Long idPrecificacao) {

    public static TemplateResponseDTO fromEntity(Template entity) {
        return new TemplateResponseDTO(
                entity.getId(), entity.getNome(), entity.getDescricao(), entity.getAtivo(),
                entity.getCategoria().getId(), entity.getAutor().getId(),
                entity.getFormato().getId(), entity.getPrecificacao().getId());
    }
}