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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

public class JogadorTests {

    @InjectMocks
    private JogadorService jogadorService;

    @Mock
    private JogadorRepository jogadorRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarJogador() throws Exception {
        // Criar um jogador fictício
        JogadorCreateDTO jogadorCreateDTO = new JogadorCreateDTO("Nome do Jogador", "2023-09-30T14:30");

        // Criar um objeto LocalDateTime fictício
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(jogadorCreateDTO.getDataEHoraLivrePraJogar(), formatter);

        // Configurar o comportamento esperado do serviço
        Jogador jogadorFicticio = new Jogador();
        jogadorFicticio.setId(1);
        jogadorFicticio.setNomeJogador("Nome do Jogador");
        jogadorFicticio.setDataEHoraLivrePraJogar(localDateTime);

        // Simular o retorno de um usuário fictício pelo serviço de usuário
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario());

        // Configurar o retorno esperado do repositório
        when(jogadorRepository.save(any())).thenReturn(jogadorFicticio);

        // Configurar o retorno esperado da conversão do objeto
        when(objectMapper.convertValue(any(), eq(JogadorDTO.class))).thenReturn(new JogadorDTO());

        // Chamar o método da serviço para criar um jogador
        JogadorDTO jogadorDTO = jogadorService.criarJogador(1, jogadorCreateDTO);

        // Verificar se o jogador criado corresponde ao jogador fictício
        assertEquals(1, jogadorDTO.getId());
        assertEquals("Nome do Jogador", jogadorDTO.getNomeJogador());
        assertEquals(localDateTime, jogadorDTO.getDataEHoraLivrePraJogar());
    }

    @Test
    public void testBuscarTodosOsJogadores() throws Exception {
        // Criar uma lista de jogadores fictícia
        List<Jogador> jogadoresFicticios = new ArrayList<>();
        jogadoresFicticios.add(new Jogador()); // Adicione jogadores fictícios aqui

        // Configurar o comportamento esperado do repositório
        when(jogadorRepository.findAll()).thenReturn(jogadoresFicticios);

        // Simular o retorno de um usuário fictício pelo serviço de usuário
        when(usuarioService.buscarPorId(anyInt())).thenReturn(new Usuario());

        // Configurar o retorno esperado da conversão do objeto
        when(objectMapper.convertValue(any(), eq(JogadorDTO.class))).thenReturn(new JogadorDTO());

        // Chamar o método da serviço para buscar todos os jogadores
        List<JogadorDTO> jogadorDTOs = jogadorService.buscarTodosOsJogadores();

        // Verificar se a lista de DTOs não está vazia
        assertFalse(jogadorDTOs.isEmpty());
        // Verificar se o número de DTOs corresponde ao número de jogadores fictícios
        assertEquals(jogadoresFicticios.size(), jogadorDTOs.size());
    }

    @Test
    public void testDeletarPorId() {
        // Chamar o método da serviço para deletar um jogador pelo ID
        jogadorService.deletarPorId(1);

        // Verificar se o método deleteById do repositório foi chamado com o ID correto
        verify(jogadorRepository, times(1)).deleteById(1);
    }

    @Test
    public void testEditaJogadorPorId() throws Exception {
        // Criar um objeto JogadorUpdateDTO fictício
        JogadorUpdateDTO jogadorUpdateDTO = new JogadorUpdateDTO("Novo Nome do Jogador");

        // Criar um jogador fictício
        Jogador jogadorFicticio = new Jogador();
        jogadorFicticio.setId(1);
        jogadorFicticio.setNomeJogador("Nome do Jogador Antigo");

        // Configurar o comportamento esperado do repositório
        when(jogadorRepository.findById(anyInt())).thenReturn(Optional.of(jogadorFicticio));
        when(jogadorRepository.save(any())).thenReturn(jogadorFicticio);

        // Chamar o método da serviço para editar um jogador pelo ID
        jogadorService.editaJogadorPorId(1, jogadorUpdateDTO);

        // Verificar se o jogador foi atualizado com o novo nome
        assertEquals("Novo Nome do Jogador", jogadorFicticio.getNomeJogador());
    }
}
