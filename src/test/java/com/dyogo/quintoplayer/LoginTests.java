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
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class LoginTests {

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
    public void testFazerLogin() {
        // Criar um exemplo de LoginDTO
        LoginDTO loginDTO = new LoginDTO("example@email.com", "senha");

        // Criar um exemplo de Login
        Login login = new Login();
        login.setEmail("example@email.com");
        login.setSenha("senha");

        // Definir o comportamento do mock do loginRepository
        when(loginRepository.findLoginByEmailAndSenha("example@email.com", "senha")).thenReturn(java.util.Optional.of(login));

        // Definir o comportamento do mock do objectMapper
        when(objectMapper.convertValue(login, LoginResponseDTO.class)).thenReturn(new LoginResponseDTO("example", "12345"));

        // Chamar o método fazerLogin
        LoginResponseDTO response = loginService.fazerLogin(loginDTO);

        // Verificar se o resultado é o esperado
        assertNotNull(response);
        assertEquals("example", response.getNome());
        assertEquals("12345", response.getNumeroDeCelular());

        // Verificar se os métodos dos mocks foram chamados corretamente
        verify(loginRepository, times(1)).findLoginByEmailAndSenha("example@email.com", "senha");
        verify(objectMapper, times(1)).convertValue(login, LoginResponseDTO.class);
    }
}

