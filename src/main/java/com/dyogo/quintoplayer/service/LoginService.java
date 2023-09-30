package com.dyogo.quintoplayer.service;

import com.dyogo.quintoplayer.dto.LoginDTO;
import com.dyogo.quintoplayer.dto.LoginResponseDTO;
import com.dyogo.quintoplayer.model.Login;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.LoginRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginRepository loginRepository;
    private final ObjectMapper objectMapper;

    public LoginService(LoginRepository loginRepository, ObjectMapper objectMapper) {
        this.loginRepository = loginRepository;
        this.objectMapper = objectMapper;

    }

    /**
     * Realiza o processo de login de um usuário.
     *
     * @param loginDTO Dados de login do usuário.
     * @return Uma instância de LoginResponseDTO contendo os detalhes da resposta de login.
     * @throws RuntimeException Se o login for recusado (credenciais inválidas).
     */

    public LoginResponseDTO fazerLogin(LoginDTO loginDTO) {
        try {
            Login login = loginRepository.findLoginByEmailAndSenha(loginDTO.email(), loginDTO.senha())
                    .orElseThrow(() -> new RuntimeException(""));


            LoginResponseDTO loginResponseDTO = objectMapper.convertValue(login, LoginResponseDTO.class);

            return loginResponseDTO;
        } catch (Exception e) {
            throw new RuntimeException("Login recusado!");
        }
    }

    /**
     * Cria um novo login de usuário.
     *
     * @param email           O email do usuário.
     * @param senha           A senha do usuário.
     * @param nome            O nome do usuário.
     * @param numeroDeCelular O número de celular do usuário.
     */

    public void criarLogin(String email, String senha, String nome, String numeroDeCelular) {
        Login login = new Login();
        login.setEmail(email);
        login.setSenha(senha);
        login.setNome(nome);
        login.setNumeroDeCelular(numeroDeCelular);

        loginRepository.save(login);
    }

}

