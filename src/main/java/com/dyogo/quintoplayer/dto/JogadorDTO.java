package com.dyogo.quintoplayer.dto;

import java.time.LocalDateTime;

/**
 * DTO que representa os detalhes de um jogador.
 */
public record JogadorDTO(
        Integer id,                               // O ID do jogador.
        String nomeJogador,                       // O nome do jogador.
        LocalDateTime dataEHoraLivrePraJogar,   // A data e hora em que o jogador está livre para jogar.
        Integer idUsuario,                        // O ID do usuário associado ao jogador.
        String nome,                              // O nome do usuário associado ao jogador.
        String numeroDeCelular                    // O número de celular do usuário associado ao jogador.
) {
}
