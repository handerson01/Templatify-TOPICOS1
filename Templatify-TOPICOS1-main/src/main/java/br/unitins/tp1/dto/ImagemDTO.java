package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ImagemDTO(
        @NotBlank @Size(max = 500) String url,
        @Size(max = 500) String descricao,
        @NotNull @Positive Long idTemplate) {
}