package com.dyogo.quintoplayer.dto;



/**
 * DTO que representa os detalhes de uma resposta de login.
 */
public record LoginResponseDTO(
        Integer id,                   // O ID do usuário.
        String email,                 // O email do usuário.
        String senha,                 // A senha do usuário.
        String nome,                  // O nome do usuário.
        String numeroDeCelular        // O número de celular do usuário.
) {
}
