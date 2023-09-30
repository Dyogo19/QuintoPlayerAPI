package com.dyogo.quintoplayer.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * DTO que representa os dados atualizados de um jogo.
 */

public record JogoUpdateDTO(
        String descricao,                   // A nova descrição do jogo.
        String enderecoDoJogo,              // O novo endereço do jogo.
        LocalDateTime dataEHorario         // A nova data e hora do jogo.
) {
}
