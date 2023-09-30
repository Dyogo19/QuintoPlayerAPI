package com.dyogo.quintoplayer.repository;

import com.dyogo.quintoplayer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    /**
     * Encontra um usuário pelo seu email.
     *
     * @param email O email do usuário a ser encontrado.
     * @return Um Optional contendo o usuário correspondente, se encontrado.
     */
    Optional<Usuario> findByEmail(String email);

}
