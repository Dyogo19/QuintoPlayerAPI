package com.dyogo.quintoplayer.service;

import com.dyogo.quintoplayer.dto.JogadorCreateDTO;
import com.dyogo.quintoplayer.dto.JogadorDTO;
import com.dyogo.quintoplayer.dto.JogadorUpdateDTO;
import com.dyogo.quintoplayer.model.Jogador;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.JogadorRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class JogadorService {

    private final JogadorRepository jogadorRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public JogadorService(JogadorRepository jogadorRepository, UsuarioService usuarioService, ObjectMapper objectMapper) {
        this.jogadorRepository = jogadorRepository;
        this.usuarioService = usuarioService;
        this.objectMapper = objectMapper;
    }

    /**
     * Cria um novo jogador associado a um usuário específico.
     *
     * @param idUsuario        O identificador do usuário ao qual o jogador será associado.
     * @param jogadorCreateDTO Dados do jogador a ser criado.
     * @return Uma instância de JogadorDTO contendo os detalhes do jogador recém-criado.
     * @throws Exception Se ocorrer algum erro durante a criação do jogador.
     */
    public JogadorDTO criarJogador(Integer idUsuario, JogadorCreateDTO jogadorCreateDTO) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(jogadorCreateDTO.dataEHoraLivrePraJogar(), formatter);
            Jogador jogador = objectMapper.convertValue(jogadorCreateDTO, Jogador.class);
            jogador.setDataEHoraLivrePraJogar(localDateTime);

            jogador.setUsuario(usuarioService.buscarPorId(idUsuario));
            JogadorDTO jogadorDTO = objectMapper.convertValue(jogadorRepository.save(jogador), JogadorDTO.class);
            return jogadorDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception();
        }

    }

    /**
     * Busca todos os jogadores.
     *
     * @return Uma lista de JogadorDTO contendo os detalhes de todos os jogadores.
     */

    public List<JogadorDTO> buscarTodosOsJogadores() {
        return jogadorRepository.findAll().stream()
                .map(jogador -> {

                    try {

                        Usuario usuario = usuarioService.buscarPorId(jogador.getUsuario().getId());
                        JogadorDTO jogadorDTO = new JogadorDTO(jogador.getId(),
                                jogador.getNomeJogador(),
                                jogador.getDataEHoraLivrePraJogar(),
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getNumeroDeCelular());
                        return jogadorDTO;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toList());
    }


    /**
     * Deleta um jogador pelo ID.
     *
     * @param id O ID do jogador a ser deletado.
     */
    public void deletarPorId(Integer id) {
        jogadorRepository.deleteById(id);
    }

    /**
     * Edita um jogador pelo ID.
     *
     * @param id               O ID do jogador a ser editado.
     * @param jogadorUpdateDTO Dados atualizados do jogador.
     * @throws Exception Se o jogador não for encontrado.
     */
    public void editaJogadorPorId(Integer id, JogadorUpdateDTO jogadorUpdateDTO) throws Exception {
        Jogador jogador = jogadorRepository.findById(id).orElseThrow(() -> new Exception("Jogador não encontrado!"));
        jogador.setId(id);
        jogador.setNomeJogador(jogadorUpdateDTO.nomeJogador());
        Jogador jogadorRetorno = jogadorRepository.save(jogador);
    }

}
