package com.dyogo.quintoplayer.dto;


import java.time.LocalDateTime;

/**
 * DTO que representa os dados atualizados de um jogador.
 */
public record JogadorUpdateDTO(
        String nomeJogador,                        // O novo nome do jogador.
        LocalDateTime dataEHoraLivrePraJogar     // A nova data e hora em que o jogador está livre para jogar.
) {
}
