package com.dyogo.quintoplayer.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO que representa os dados necessários para criar um novo usuário.
 */

public record UsuarioCreateDTO(
        String nome,              // O nome do usuário.
        String email,             // O email do usuário.
        String senha,             // A senha do usuário.
        String numeroDeCelular    // O número de celular do usuário.
) {
}
