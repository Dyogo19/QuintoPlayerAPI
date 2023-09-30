package com.dyogo.quintoplayer.controller;

import com.dyogo.quintoplayer.dto.JogadorCreateDTO;
import com.dyogo.quintoplayer.dto.JogadorDTO;
import com.dyogo.quintoplayer.dto.JogadorUpdateDTO;
import com.dyogo.quintoplayer.service.JogadorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/v1/jogadores")
@CrossOrigin(origins = "*")
public class JogadorController {

    private JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    /**
     * Busca todos os jogadores.
     *
     * @return ResponseEntity contendo uma lista de JogadorDTO com os detalhes de todos os jogadores.
     */
    @GetMapping
    public ResponseEntity<List<JogadorDTO>> buscarTodosOsJogadores() {
        return new ResponseEntity<>(jogadorService.buscarTodosOsJogadores(), HttpStatus.OK);
    }

    /**
     * Cria um novo jogador associado a um usuário específico.
     * <p>
     * Este endpoint permite a criação de um novo jogador associado a um usuário com base nos dados fornecidos no corpo da solicitação.
     *
     * @param idUsuario        O identificador do usuário ao qual o jogador será associado.
     * @param jogadorCreateDTO Os dados do jogador a serem criados, especificados no corpo da solicitação no formato JSON.
     * @return ResponseEntity contendo uma instância de JogadorDTO com os detalhes do jogador recém-criado.
     * @throws Exception Se ocorrer algum erro durante a criação do jogador, uma exceção pode ser lançada.
     */
    @PostMapping("{idUsuario}")
    public ResponseEntity<JogadorDTO> criarJogador(@PathVariable("idUsuario") Integer idUsuario, @RequestBody JogadorCreateDTO jogadorCreateDTO) throws Exception {
        return ResponseEntity.ok(this.jogadorService.criarJogador(idUsuario, jogadorCreateDTO));
    }

    /**
     * Deleta um jogador pelo ID.
     *
     * @param id O ID do jogador a ser deletado.
     * @return ResponseEntity indicando que a solicitação foi aceita.
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Integer id) {
        jogadorService.deletarPorId(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Edita um jogador pelo ID.
     *
     * @param id               O ID do jogador a ser editado.
     * @param jogadorUpdateDTO Dados atualizados do jogador.
     * @return ResponseEntity indicando que a solicitação foi processada com sucesso.
     * @throws Exception Se ocorrer um erro durante a edição do jogador.
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> editaJogador(@PathVariable("id") Integer id,
                                             @RequestBody JogadorUpdateDTO jogadorUpdateDTO) throws Exception {
        jogadorService.editaJogadorPorId(id, jogadorUpdateDTO);
        return ResponseEntity.noContent().build();
    }

}
