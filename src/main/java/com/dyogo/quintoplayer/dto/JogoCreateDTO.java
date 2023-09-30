package com.dyogo.quintoplayer.dto;

/**
 * DTO que representa os dados necessários para criar um novo jogo.
 */
public record JogoCreateDTO(
        String descricao,          // A descrição do jogo.
        String enderecoDoJogo,     // O endereço do jogo.
        String dataEHorario       // A data e horário do jogo.
) {
}
