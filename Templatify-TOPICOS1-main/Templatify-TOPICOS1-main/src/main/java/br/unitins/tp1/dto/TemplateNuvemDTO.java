package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TemplateNuvemDTO(
        @NotBlank @Size(min = 2, max = 100) String nome,
        @Size(max = 1000) String descricao,
        @NotNull @Positive Long idCategoria,
        @NotNull @Positive Long idAutor,
        @NotNull @Positive Long idFormato,
        @NotNull @Positive Long idPrecificacao,
        Boolean ativo,
        @NotBlank @Size(max = 1000) String urlAcesso,
        @NotBlank @Size(max = 100) String plataforma,
        @NotNull Boolean requerContaPaga) {
}