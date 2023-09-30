package com.dyogo.quintoplayer.dto;

import java.time.LocalDateTime;

/**
 * DTO que representa os detalhes de um jogo.
 */
public record JogoDTO(
        Integer id,                              // O ID do jogo.
        String descricao,                        // A descrição do jogo.
        String enderecoDoJogo,                   // O endereço do jogo.
        LocalDateTime dataEHorario,             // A data e hora do jogo.
        Integer idUsuario,                      // O ID do usuário associado ao jogo.
        String nome,                            // O nome do usuário associado ao jogo.
        String numeroDeCelular                  // O número de celular do usuário associado ao jogo.
) {
}
