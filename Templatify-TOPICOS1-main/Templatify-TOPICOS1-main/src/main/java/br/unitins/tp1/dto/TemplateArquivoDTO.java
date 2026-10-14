package br.unitins.tp1.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record TemplateArquivoDTO(
    @NotBlank(message = "O nome deve ser informado.")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres.")
    String nome,

    @Size(max = 1000, message = "A descrição deve ter no máximo 1000 caracteres.")
    String descricao,

    @NotNull(message = "A categoria deve ser informada.")
    @Positive(message = "ID de categoria inválido.")
    Long idCategoria,

    @NotNull(message = "O formato deve ser informado.")
    @Positive(message = "ID de formato inválido.")
    Long idFormato,

    @NotNull(message = "A precificação deve ser informada.")
    @Positive(message = "ID de precificação inválido.")
    Long idPrecificacao,

    @NotNull(message = "O autor deve ser informado.")
    @Positive(message = "ID de autor inválido.")
    Long idAutor,

    Boolean ativo,

    @NotBlank(message = "A URL do arquivo deve ser informada.")
    String arquivoUrl,

    @NotNull(message = "O tamanho do arquivo deve ser informado.")
    @Positive(message = "Tamanho de arquivo inválido.")
    Long tamanhoBytes,

    String checksum
) {
}
