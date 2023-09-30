package com.dyogo.quintoplayer.service;

import com.dyogo.quintoplayer.dto.JogoCreateDTO;
import com.dyogo.quintoplayer.dto.JogoDTO;
import com.dyogo.quintoplayer.dto.JogoUpdateDTO;
import com.dyogo.quintoplayer.model.Jogo;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.JogoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JogoService {

    private final JogoRepository jogoRepository;
    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;

    public JogoService(JogoRepository jogoRepository, ObjectMapper objectMapper, UsuarioService usuarioService) {
        this.jogoRepository = jogoRepository;
        this.objectMapper = objectMapper;
        this.usuarioService = usuarioService;
    }

    /**
     * Cria um novo jogo associado a um usuário específico.
     *
     * @param idUsuario     O identificador do usuário ao qual o jogo será associado.
     * @param jogoCreateDTO Dados do jogo a ser criado.
     * @return Uma instância de JogoDTO contendo os detalhes do jogo recém-criado.
     * @throws Exception Se ocorrer algum erro durante a criação do jogo.
     */
    public JogoDTO criarJogo(Integer idUsuario, JogoCreateDTO jogoCreateDTO) throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(jogoCreateDTO.dataEHorario(), formatter);
            Jogo jogo = objectMapper.convertValue(jogoCreateDTO, Jogo.class);
            jogo.setDataEHorario(localDateTime);

            jogo.setUsuario(usuarioService.buscarPorId(idUsuario));
            JogoDTO jogoDTO = objectMapper.convertValue(jogoRepository.save(jogo), JogoDTO.class);
            return jogoDTO;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception();
        }

    }


    /**
     * Busca todos os jogos.
     *
     * @return Uma lista de JogoDTO contendo os detalhes de todos os jogos.
     */

    public List<JogoDTO> buscarTodosOsJogos() {
        return jogoRepository.findAll().stream()
                .map(jogo -> {

                    try {

                        Usuario usuario = usuarioService.buscarPorId(jogo.getUsuario().getId());
                        JogoDTO jogoDTO = new JogoDTO(jogo.getId(),
                                jogo.getDescricao(),
                                jogo.getEnderecoDoJogo(),
                                jogo.getDataEHorario(),
                                usuario.getId(),
                                usuario.getNome(),
                                usuario.getNumeroDeCelular());
                        return jogoDTO;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                })
                .collect(Collectors.toList());
    }

    /**
     * Deleta um jogo pelo ID.
     *
     * @param id O ID do jogo a ser deletado.
     */
    public void deletarPorId(Integer id) {
        jogoRepository.deleteById(id);
    }

    /**
     * Edita um jogo pelo ID.
     *
     * @param id            O ID do jogo a ser editado.
     * @param jogoUpdateDTO Dados atualizados do jogo.
     * @throws Exception Se o jogo não for encontrado.
     */
    public void editaJogoPorId(Integer id, JogoUpdateDTO jogoUpdateDTO) throws Exception {
        Jogo jogo = jogoRepository.findById(id).orElseThrow(() -> new Exception("Jogo não encontrado!"));
        jogo.setId(id);
        jogo.setDescricao(jogoUpdateDTO.descricao());
        jogo.setEnderecoDoJogo(jogoUpdateDTO.enderecoDoJogo());
        jogo.setDataEHorario(jogoUpdateDTO.dataEHorario());
        Jogo jogoRetorno = jogoRepository.save(jogo);
    }

}
