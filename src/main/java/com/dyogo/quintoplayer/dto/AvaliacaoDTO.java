package com.dyogo.quintoplayer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO que representa os detalhes de uma avaliação.
 */

public record AvaliacaoDTO(
        Integer id,         // O ID da avaliação.
        String avaliacao,   // O texto da avaliação.
        Integer nota        // A nota da avaliação.
) {
}
