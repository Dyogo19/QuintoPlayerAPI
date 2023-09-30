package com.dyogo.quintoplayer.dto;


/**
 * DTO que representa os dados de login, incluindo email e senha.
 */
public record LoginDTO(
        String email,    // O email do usuário para o login.
        String senha     // A senha do usuário para o login.
) {
}
