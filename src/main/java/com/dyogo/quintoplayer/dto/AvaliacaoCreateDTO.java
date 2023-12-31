package com.dyogo.quintoplayer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO que representa os dados necessários para criar uma nova avaliação.
 */

public record AvaliacaoCreateDTO(
        String avaliacao,                 // O texto da avaliação.
        Integer nota,                    // A nota da avaliação.
        Integer idUsuarioDestinatario    // O ID do usuário destinatário da avaliação.
) {
}

