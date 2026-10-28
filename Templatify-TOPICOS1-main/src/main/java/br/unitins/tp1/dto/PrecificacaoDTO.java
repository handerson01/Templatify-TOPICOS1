package br.unitins.tp1.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrecificacaoDTO(
        @NotNull @DecimalMin("0.0") @Digits(integer = 17, fraction = 2) BigDecimal valorBase,
        @NotBlank @Size(min = 3, max = 3) String moeda,
        @DecimalMin("0.0") @DecimalMax("100.0") @Digits(integer = 3, fraction = 2) BigDecimal percentualDesconto,
        LocalDateTime inicioPromocao,
        LocalDateTime fimPromocao) {
}