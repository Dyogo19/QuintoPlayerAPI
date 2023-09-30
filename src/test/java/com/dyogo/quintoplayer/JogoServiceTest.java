package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.JogoCreateDTO;
import com.dyogo.quintoplayer.dto.JogoDTO;
import com.dyogo.quintoplayer.model.Jogo;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.JogoRepository;
import com.dyogo.quintoplayer.service.JogoService;
import com.dyogo.quintoplayer.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class JogoServiceTest {

    @Mock
    private JogoRepository jogoRepository;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private JogoService jogoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarJogo() throws Exception {
        // Defina seus objetos de teste, por exemplo, um JogoCreateDTO e um usuário de teste
        JogoCreateDTO jogoCreateDTO = new JogoCreateDTO("a","b","2023-09-30T10:00");
        jogoCreateDTO.dataEHorario(); // Substitua a data e hora corretas

        // Defina o comportamento esperado para os mocks
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario()); // Simule a busca de um usuário

        }

    @Test
    public void testBuscarTodosOsJogos() {
        // Defina o comportamento esperado para os mocks
        when(jogoRepository.findAll()).thenReturn(new ArrayList<>()); // Simule uma lista vazia de jogos

        // Chame o método que você deseja testar
        List<JogoDTO> resultado = jogoService.buscarTodosOsJogos();

        // Verifique se os métodos e interações esperados ocorreram
        verify(jogoRepository, times(1)).findAll(); // Verifique se o método findAll foi chamado uma vez
    }

}

