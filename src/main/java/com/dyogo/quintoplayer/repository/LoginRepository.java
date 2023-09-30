package com.dyogo.quintoplayer.repository;

import com.dyogo.quintoplayer.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {


    /**
     * Encontra um registro de login com base no email e senha fornecidos.
     *
     * @param email O email do usuário.
     * @param senha A senha do usuário.
     * @return Um Optional contendo o registro de login correspondente, se encontrado.
     */
    Optional<Login> findLoginByEmailAndSenha(String email, String senha);

}
