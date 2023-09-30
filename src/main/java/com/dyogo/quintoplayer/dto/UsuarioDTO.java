package com.dyogo.quintoplayer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO que representa os detalhes de um usuário.
 */

public record UsuarioDTO(
        Integer id,                  // O ID do usuário.
        String nome,                 // O nome do usuário.
        String email,                // O email do usuário.
        String numeroDeCelular       // O número de celular do usuário.
) {
}
