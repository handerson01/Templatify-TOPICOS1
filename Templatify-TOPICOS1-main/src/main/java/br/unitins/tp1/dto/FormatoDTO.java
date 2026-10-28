package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record FormatoDTO(
        @NotBlank @Size(max = 80) String nome,
        @NotBlank @Size(max = 20) String extensao) {
}