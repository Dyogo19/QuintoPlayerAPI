package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.JogadorCreateDTO;
import com.dyogo.quintoplayer.dto.JogadorDTO;
import com.dyogo.quintoplayer.dto.JogadorUpdateDTO;
import com.dyogo.quintoplayer.model.Jogador;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.JogadorRepository;
import com.dyogo.quintoplayer.service.JogadorService;
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

import static org.mockito.Mockito.*;

public class JogadorServiceTest {

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private JogadorService jogadorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarJogador() throws Exception {
        // Defina seus objetos de teste, por exemplo, um JogadorCreateDTO e um usuário de teste
        JogadorCreateDTO jogadorCreateDTO = new JogadorCreateDTO("AB", "2023-09-30T10:00");
        jogadorCreateDTO.dataEHoraLivrePraJogar(); // Substitua a data e hora corretas

        // Defina o comportamento esperado para os mocks
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario()); // Simule a busca de um usuário

        }

    @Test
    public void testBuscarTodosOsJogadores() {
        // Defina o comportamento esperado para os mocks
        when(jogadorRepository.findAll()).thenReturn(new ArrayList<>()); // Simule uma lista vazia de jogadores


        // Chame o método que você deseja testar
        List<JogadorDTO> resultado = jogadorService.buscarTodosOsJogadores();

        // Verifique se os métodos e interações esperados ocorreram
        verify(jogadorRepository, times(1)).findAll(); // Verifique se o método findAll foi chamado uma vez
        // Você pode adicionar mais verificações conforme necessário
    }


}

