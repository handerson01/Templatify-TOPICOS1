package br.unitins.tp1.dto;

import br.unitins.tp1.model.Imagem;

public record ImagemResponseDTO(Long id, String url, String descricao, Long idTemplate) {
    public static ImagemResponseDTO fromEntity(Imagem imagem) {
        return new ImagemResponseDTO(imagem.getId(), imagem.getUrl(), imagem.getDescricao(),
                imagem.getTemplate().getId());
    }
}