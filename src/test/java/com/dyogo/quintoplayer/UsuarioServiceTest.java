package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.UsuarioCreateDTO;
import com.dyogo.quintoplayer.dto.UsuarioDTO;
import com.dyogo.quintoplayer.dto.UsuarioUpdateDTO;
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

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

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
        // Defina seu objeto de teste, por exemplo, um UsuarioCreateDTO
        UsuarioCreateDTO usuarioCreateDTO = new UsuarioCreateDTO("a","a@gmail.com","123456","51988776655");

        // Defina o comportamento esperado para o mock do repositório
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(new Usuario());

        // Simule a conversão de objetos
        when(objectMapper.convertValue(any(), eq(UsuarioDTO.class))).thenReturn(new UsuarioDTO(1,"a","a@gmail.com","51988776655"));


    }

    @Test
    public void testBuscarTodosOsUsuarios() {
        // Defina o comportamento esperado para o mock do repositório
        when(usuarioRepository.findAll()).thenReturn(new ArrayList<>()); // Simule uma lista vazia de usuários

        // Simule a conversão de objetos
        when(objectMapper.convertValue(any(), eq(UsuarioDTO.class))).thenReturn(new UsuarioDTO(1,"a","a@gmail.com","51988776655"));

        // Chame o método que você deseja testar
        Iterable<UsuarioDTO> resultado = usuarioService.buscarTodosOsUsuarios();

        // Verifique se os métodos e interações esperados ocorreram
        assertNotNull(resultado); // Verifique se o resultado não é nulo
        verify(usuarioRepository, times(1)).findAll(); // Verifique se o método findAll foi chamado uma vez
        // Você pode adicionar mais verificações conforme necessário
    }


    @Test
    public void testBuscarPorId() throws Exception {
        // Defina o comportamento esperado para o mock do repositório
        Usuario usuarioExistente = new Usuario();
        when(usuarioRepository.findById(anyInt())).thenReturn(Optional.of(usuarioExistente)); // Simule a busca de um usuário

        // Chame o método que você deseja testar
        Usuario resultado = usuarioService.buscarPorId(1); // Substitua 1 pelo ID correto

        // Verifique se o resultado não é nulo
        assertNotNull(resultado);
    }

}

