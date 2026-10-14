package br.unitins.tp1.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrecificacaoDTO(
        @NotNull @DecimalMin("0.0") Double valorBase,
        @NotBlank @Size(min = 3, max = 3) String moeda,
        @DecimalMin("0.0") @DecimalMax("100.0") Double percentualDesconto,
        LocalDateTime inicioPromocao,
        LocalDateTime fimPromocao) {
}