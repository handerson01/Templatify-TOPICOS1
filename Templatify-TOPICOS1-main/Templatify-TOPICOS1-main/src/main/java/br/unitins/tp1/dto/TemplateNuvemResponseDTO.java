package br.unitins.tp1.dto;

import br.unitins.tp1.model.TemplateNuvem;

public record TemplateNuvemResponseDTO(
        Long id,
        String nome,
        String descricao,
        Long idCategoria,
        Long idAutor,
        Long idFormato,
        Long idPrecificacao,
        Boolean ativo,
        String urlAcesso,
        String plataforma,
        Boolean requerContaPaga) {

    public static TemplateNuvemResponseDTO fromEntity(TemplateNuvem entity) {
        return new TemplateNuvemResponseDTO(
                entity.getId(), entity.getNome(), entity.getDescricao(),
                entity.getCategoria().getId(), entity.getAutor().getId(),
                entity.getFormato().getId(), entity.getPrecificacao().getId(),
                entity.getAtivo(), entity.getUrlAcesso(), entity.getPlataforma(),
                entity.getRequerContaPaga());
    }
}