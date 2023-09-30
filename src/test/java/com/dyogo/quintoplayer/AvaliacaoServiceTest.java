package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.AvaliacaoCreateDTO;
import com.dyogo.quintoplayer.dto.AvaliacaoDTO;
import com.dyogo.quintoplayer.model.Avaliacao;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.AvaliacaoRepository;
import com.dyogo.quintoplayer.service.AvaliacaoService;
import com.dyogo.quintoplayer.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AvaliacaoServiceTest {

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ObjectMapper objectMapper;

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarAvaliacao() throws Exception {
        // Defina seus objetos de teste, por exemplo, um AvaliacaoCreateDTO e um usuário de teste
        AvaliacaoCreateDTO avaliacaoCreateDTO = new AvaliacaoCreateDTO("a",2, 1);
        avaliacaoCreateDTO.idUsuarioDestinatario(); // Substitua 1 pelo ID correto

        // Defina o comportamento esperado para os mocks
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario()); // Simule a busca de um usuário

        // Simule a conversão de objetos
        when(objectMapper.convertValue(eq(avaliacaoCreateDTO), eq(Avaliacao.class))).thenReturn(new Avaliacao());
        when(objectMapper.convertValue(any(), eq(AvaliacaoDTO.class))).thenReturn(new AvaliacaoDTO(1,"a",2));

        // Chame o método que você deseja testar
        AvaliacaoDTO resultado = avaliacaoService.criarAvaliacao(1, avaliacaoCreateDTO); // Substitua 1 pelo ID correto

        // Verifique se os métodos e interações esperados ocorreram
        verify(usuarioService, times(2)).buscarPorId(anyInt()); // Verifique se o método buscarPorId foi chamado duas vezes
        verify(avaliacaoRepository, times(1)).save(any(Avaliacao.class)); // Verifique se o método save foi chamado uma vez
    }

    @Test
    public void testObterAvaliacoesPorUsuario() throws Exception {
        // Defina o comportamento esperado para os mocks
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario()); // Simule a busca de um usuário
        when(avaliacaoRepository.findAvaliacaoByUsuarioDestinado_Id(anyInt())).thenReturn(new ArrayList<>()); // Simule a lista vazia de avaliações

        // Simule a conversão de objetos
        when(objectMapper.convertValue(any(), eq(AvaliacaoDTO.class))).thenReturn(new AvaliacaoDTO(1,"a",2));

        // Chame o método que você deseja testar
        List<AvaliacaoDTO> resultado = avaliacaoService.obterAvaliacoesPorUsuario(1); // Substitua 1 pelo ID correto

        }

    @Test
    public void testObterAvaliacoesPorUsuarioDestinatario() throws Exception {
        // Defina o comportamento esperado para os mocks
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario()); // Simule a busca de um usuário
        when(avaliacaoRepository.findAvaliacaoByUsuario_Id(anyInt())).thenReturn(new ArrayList<>()); // Simule a lista vazia de avaliações

        // Simule a conversão de objetos
        when(objectMapper.convertValue(any(), eq(AvaliacaoDTO.class))).thenReturn(new AvaliacaoDTO(1,"a",2));

        // Chame o método que você deseja testar
        List<AvaliacaoDTO> resultado = avaliacaoService.obterAvaliacoesPorUsuarioDestinatario(1); // Substitua 1 pelo ID correto
    }
}

