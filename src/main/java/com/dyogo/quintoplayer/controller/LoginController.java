package com.dyogo.quintoplayer.controller;

import com.dyogo.quintoplayer.dto.LoginDTO;
import com.dyogo.quintoplayer.dto.LoginResponseDTO;
import com.dyogo.quintoplayer.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/login")
@CrossOrigin(origins = "*")

public class LoginController {

    private final LoginService loginService;


    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Realiza o processo de login de um usuário.
     * <p>
     * Este endpoint permite que um usuário faça login no sistema fornecendo suas credenciais de login no corpo da solicitação.
     *
     * @param loginDTO Os dados de login do usuário, especificados no corpo da solicitação no formato JSON.
     * @return ResponseEntity contendo uma instância de LoginResponseDTO com os detalhes da resposta de login.
     */
    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO loginDTO) {
        return new ResponseEntity<>(loginService.fazerLogin(loginDTO), HttpStatus.OK);
    }
}
