package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.LoginDTO;
import com.dyogo.quintoplayer.dto.LoginResponseDTO;
import com.dyogo.quintoplayer.model.Login;
import com.dyogo.quintoplayer.repository.LoginRepository;
import com.dyogo.quintoplayer.service.LoginService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginServiceTest {

    @Mock
    private LoginRepository loginRepository;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFazerLoginComCredenciaisCorretas() {
        // Defina seus objetos de teste, por exemplo, um LoginDTO e um Login
        LoginDTO loginDTO = new LoginDTO("usuario@example.com", "senhaCorreta");
        Login login = new Login();
        login.setEmail("usuario@example.com");
        login.setSenha("senhaCorreta");

        // Defina o comportamento esperado para o mock do repositório
        when(loginRepository.findLoginByEmailAndSenha(loginDTO.email(), loginDTO.senha())).thenReturn(Optional.of(login));

        // Simule a conversão de objetos
        when(objectMapper.convertValue(eq(login), eq(LoginResponseDTO.class))).thenReturn(new LoginResponseDTO(1, "a@gmail.com","123456","A","51988776655"));

        // Chame o método que você deseja testar
        LoginResponseDTO resultado = loginService.fazerLogin(loginDTO);

        // Verifique se os métodos e interações esperados ocorreram
        assertNotNull(resultado); // Verifique se a resposta não é nula
    }

    @Test
    public void testFazerLoginComCredenciaisIncorretas() {
        // Defina um objeto de teste com credenciais incorretas
        LoginDTO loginDTO = new LoginDTO("usuario@example.com", "senhaIncorreta");

        // Defina o comportamento esperado para o mock do repositório
        when(loginRepository.findLoginByEmailAndSenha(loginDTO.email(), loginDTO.senha())).thenReturn(Optional.empty());

        // Chame o método que você deseja testar
        assertThrows(RuntimeException.class, () -> loginService.fazerLogin(loginDTO));
        // Verifique se uma exceção é lançada quando as credenciais são incorretas
    }

    @Test
    public void testCriarLogin() {
        // Defina os dados do usuário para criação
        String email = "novo_usuario@example.com";
        String senha = "novaSenha";
        String nome = "Novo Usuário";
        String numeroDeCelular = "+1234567890";

        // Chame o método que você deseja testar
        loginService.criarLogin(email, senha, nome, numeroDeCelular);

        // Verifique se o método save do repositório foi chamado
        verify(loginRepository, times(1)).save(any(Login.class));
    }
}
