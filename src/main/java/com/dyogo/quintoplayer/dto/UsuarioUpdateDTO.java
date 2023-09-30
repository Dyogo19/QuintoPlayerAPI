package com.dyogo.quintoplayer.dto;

/**
 * DTO que representa os dados atualizados de um usuário.
 */
public record UsuarioUpdateDTO(
        String nome,                 // O novo nome do usuário.
        String email,                // O novo email do usuário.
        String senha,                // A nova senha do usuário.
        String numeroDeCelular       // O novo número de celular do usuário.
) {
}

