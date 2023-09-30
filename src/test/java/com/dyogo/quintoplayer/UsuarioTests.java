package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.UsuarioCreateDTO;
import com.dyogo.quintoplayer.dto.UsuarioDTO;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.UsuarioRepository;
import com.dyogo.quintoplayer.service.LoginService;
import com.dyogo.quintoplayer.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTests {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private LoginService loginService;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarUsuario() {
        // Criar um exemplo de UsuarioCreateDTO
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO("example@email.com", "senha", "Nome", "12345");

        // Criar um exemplo de Usuario
        Usuario usuario = new Usuario();
        usuario.setEmail("example@email.com");
        usuario.setSenha("senha");
        usuario.setNome("Nome");
        usuario.setNumeroDeCelular("12345");

        // Definir o comportamento dos mocks
        when(objectMapper.convertValue(usuarioCreateDTO, Usuario.class)).thenReturn(usuario);
        when(objectMapper.convertValue(usuario, UsuarioDTO.class)).thenReturn(new UsuarioDTO("Nome", "example@email.com", "12345"));

        // Chamar o método criarUsuario
        UsuarioDTO response = usuarioService.criarUsuario(usuarioCreateDTO);

        // Verificar se o resultado é o esperado
        assertNotNull(response);
        assertEquals("Nome", response.getNome());
        assertEquals("example@email.com", response.getEmail());
        assertEquals("12345", response.getNumeroDeCelular());

        // Verificar se os métodos dos mocks foram chamados corretamente
        verify(usuarioRepository, times(1)).save(usuario);
        verify(loginService, times(1)).criarLogin("example@email.com", "senha", "Nome", "12345");
    }
}

