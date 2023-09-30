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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class JogoTests {

    @InjectMocks
    private JogoService jogoService;

    @Mock
    private JogoRepository jogoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCriarJogo() throws Exception {
        // Crie um objeto de exemplo para JogoCreateDTO
        JogoCreateDTO jogoCreateDTO = new JogoCreateDTO();
        jogoCreateDTO.setDescricao("Jogo de Exemplo");
        jogoCreateDTO.setEnderecoDoJogo("Local de Exemplo");
        jogoCreateDTO.setDataEHorario("2023-09-30T15:00");

        // Crie um objeto de exemplo para o usuário associado ao jogo
        Usuario usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Usuário de Exemplo");
        usuario.setNumeroDeCelular("123456789");

        // Crie um objeto de exemplo para o jogo criado
        Jogo jogoCriado = new Jogo();
        jogoCriado.setId(1);
        jogoCriado.setDescricao(jogoCreateDTO.getDescricao());
        jogoCriado.setEnderecoDoJogo(jogoCreateDTO.getEnderecoDoJogo());
        jogoCriado.setDataEHorario(LocalDateTime.parse(jogoCreateDTO.getDataEHorario(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        jogoCriado.setUsuario(usuario);

        // Configure o comportamento do mock do jogoRepository
        when(jogoRepository.save(any(Jogo.class))).thenReturn(jogoCriado);

        // Configure o comportamento do mock do usuárioService
        when(usuarioService.buscarPorId(1)).thenReturn(usuario);

        // Chame o método criarJogo
        JogoDTO jogoDTO = jogoService.criarJogo(1, jogoCreateDTO);

        // Verifique se o jogo criado é igual ao jogo retornado no DTO
        assertEquals(jogoCriado.getId(), jogoDTO.getId());
        assertEquals(jogoCriado.getDescricao(), jogoDTO.getDescricao());
        assertEquals(jogoCriado.getEnderecoDoJogo(), jogoDTO.getEnderecoDoJogo());
        assertEquals(jogoCriado.getDataEHorario(), jogoDTO.getDataEHorario());
        assertEquals(jogoCriado.getUsuario().getId(), jogoDTO.getIdUsuario());
        assertEquals(jogoCriado.getUsuario().getNome(), jogoDTO.getNomeUsuario());
        assertEquals(jogoCriado.getUsuario().getNumeroDeCelular(), jogoDTO.getNumeroDeCelularUsuario());
    }

    @Test
    public void testBuscarTodosOsJogos() {
        // Crie uma lista de jogos de exemplo
        List<Jogo> jogos = new ArrayList<>();
        Jogo jogo1 = new Jogo();
        jogo1.setId(1);
        jogo1.setDescricao("Jogo 1");
        jogo1.setEnderecoDoJogo("Local 1");
        jogo1.setDataEHorario(LocalDateTime.now());
        jogo1.setUsuario(new Usuario());
        Jogo jogo2 = new Jogo();
        jogo2.setId(2);
        jogo2.setDescricao("Jogo 2");
        jogo2.setEnderecoDoJogo("Local 2");
        jogo2.setDataEHorario(LocalDateTime.now());
        jogo2.setUsuario(new Usuario());
        jogos.add(jogo1);
        jogos.add(jogo2);

        // Configure o comportamento do mock do jogoRepository
        when(jogoRepository.findAll()).thenReturn(jogos);

        // Chame o método buscarTodosOsJogos
        List<JogoDTO> jogoDTOs = jogoService.buscarTodosOsJogos();

        // Verifique se a lista de DTOs possui o mesmo tamanho da lista de jogos
        assertEquals(jogos.size(), jogoDTOs.size());
    }

    @Test
    public void testDeletarPorId() {
        int jogoId = 1;

        // Chame o método deletarPorId
        jogoService.deletarPorId(jogoId);

        // Verifique se o método de deleteById do jogoRepository foi chamado com o ID correto
        verify(jogoRepository, times(1)).deleteById(jogoId);
    }

    @Test
    public void testEditaJogoPorId() throws Exception {
        int jogoId = 1;
        JogoUpdateDTO jogoUpdateDTO = new JogoUpdateDTO();
        jogoUpdateDTO.setDescricao("Nova descrição");
        jogoUpdateDTO.setEnderecoDoJogo("Novo local");
        jogoUpdateDTO.setDataEHorario("2023-09-30T16:00");

        Jogo jogoExistente = new Jogo();
        jogoExistente.setId(jogoId);
        jogoExistente.setDescricao("Descrição Antiga");
        jogoExistente.setEnderecoDoJogo("Local Antigo");
        jogoExistente.setDataEHorario(LocalDateTime.now());
        jogoExistente.setUsuario(new Usuario());

        // Configure o comportamento do mock do jogoRepository
        when(jogoRepository.findById(jogoId)).thenReturn(java.util.Optional.of(jogoExistente));

        // Chame o método editaJogoPorId
        jogoService.editaJogoPorId(jogoId, jogoUpdateDTO);

        // Verifique se o jogo existente foi atualizado com os novos dados
        assertEquals(jogoUpdateDTO.getDescricao(), jogoExistente.getDescricao());
        assertEquals(jogoUpdateDTO.getEnderecoDoJogo(), jogoExistente.getEnderecoDoJogo());
        assertEquals(LocalDateTime.parse(jogoUpdateDTO.getDataEHorario(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")), jogoExistente.getDataEHorario());
    }
}
