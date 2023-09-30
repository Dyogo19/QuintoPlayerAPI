package com.dyogo.quintoplayer;

import com.dyogo.quintoplayer.dto.AvaliacaoCreateDTO;
import com.dyogo.quintoplayer.dto.AvaliacaoDTO;
import com.dyogo.quintoplayer.model.Avaliacao;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.AvaliacaoRepository;
import com.dyogo.quintoplayer.service.AvaliacaoService;
import com.dyogo.quintoplayer.service.UsuarioService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class AvaliacaoTests {

    @InjectMocks
    private AvaliacaoService avaliacaoService;

    @Mock
    private AvaliacaoRepository avaliacaoRepository;

    @Mock
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarAvaliacao() throws Exception {
        // Criar um objeto AvaliacaoCreateDTO de exemplo
        AvaliacaoCreateDTO avaliacaoCreateDTO = new AvaliacaoCreateDTO("Avaliação de exemplo", 5, 1);

        // Simular o comportamento do usuárioService
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario());

        // Simular o comportamento do avaliacaoRepository
        when(avaliacaoRepository.save(any())).thenReturn(new Avaliacao());

        // Chamar o método criarAvaliacao
        AvaliacaoDTO avaliacaoDTO = avaliacaoService.criarAvaliacao(1, avaliacaoCreateDTO);

        // Verificar se o método retornou um objeto AvaliacaoDTO
        assertNotNull(avaliacaoDTO);
    }

    @Test
    public void testObterAvaliacoesPorUsuario() throws Exception {
        // Simular o comportamento do usuárioService
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario());

        // Simular o comportamento do avaliacaoRepository
        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add(new Avaliacao());
        when(avaliacaoRepository.findAvaliacaoByUsuarioDestinado_Id(anyInt())).thenReturn(avaliacoes);

        // Chamar o método obterAvaliacoesPorUsuario
        List<AvaliacaoDTO> avaliacoesDTO = avaliacaoService.obterAvaliacoesPorUsuario(1);

        // Verificar se o método retornou uma lista de AvaliacaoDTOs
        assertNotNull(avaliacoesDTO);
        assertFalse(avaliacoesDTO.isEmpty());
    }

    @Test
    public void testObterAvaliacoesPorUsuarioDestinatario() throws Exception {
        // Simular o comportamento do usuárioService
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario());

        // Simular o comportamento do avaliacaoRepository
        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add(new Avaliacao());
        when(avaliacaoRepository.findAvaliacaoByUsuario_Id(anyInt())).thenReturn(avaliacoes);

        // Chamar o método obterAvaliacoesPorUsuarioDestinatario
        List<AvaliacaoDTO> avaliacoesDTO = avaliacaoService.obterAvaliacoesPorUsuarioDestinatario(1);

        // Verificar se o método retornou uma lista de AvaliacaoDTOs
        assertNotNull(avaliacoesDTO);
        assertFalse(avaliacoesDTO.isEmpty());
    }

    @Test
    public void testObterTodasAsAvaliacoes() {
        // Simular o comportamento do avaliacaoRepository
        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add(new Avaliacao());
        when(avaliacaoRepository.findAll()).thenReturn(avaliacoes);

        // Chamar o método obterTodasAsAvaliacoes
        List<Avaliacao> avaliacoesRetornadas = avaliacaoService.obterTodasAsAvaliacoes();

        // Verificar se o método retornou uma lista de Avaliacoes
        assertNotNull(avaliacoesRetornadas);
        assertFalse(avaliacoesRetornadas.isEmpty());
    }
}
