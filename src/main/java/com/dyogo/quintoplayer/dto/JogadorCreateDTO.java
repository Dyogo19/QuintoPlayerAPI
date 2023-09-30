package com.dyogo.quintoplayer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO que representa os dados necessários para criar um novo jogador.
 */

public record JogadorCreateDTO(
        String nomeJogador,                 // O nome do jogador.
        String dataEHoraLivrePraJogar      // A data e hora em que o jogador está livre para jogar.
) {
}
