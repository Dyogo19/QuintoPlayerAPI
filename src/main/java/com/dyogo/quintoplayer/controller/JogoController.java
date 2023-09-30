package com.dyogo.quintoplayer.controller;

import com.dyogo.quintoplayer.dto.JogoCreateDTO;
import com.dyogo.quintoplayer.dto.JogoDTO;
import com.dyogo.quintoplayer.dto.JogoUpdateDTO;
import com.dyogo.quintoplayer.service.JogoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/jogos")
@CrossOrigin(origins = "*")
public class JogoController {

    private JogoService jogoService;

    public JogoController(JogoService jogoService) {
        this.jogoService = jogoService;
    }

    /**
     * Busca todos os jogos.
     *
     * @return ResponseEntity contendo uma lista de JogoDTO com os detalhes de todos os jogos.
     */

    @GetMapping
    public ResponseEntity<List<JogoDTO>> buscarTodosOsJogos() {
        return new ResponseEntity<>(jogoService.buscarTodosOsJogos(), HttpStatus.OK);
    }

    /**
     * Cria um novo jogo associado a um usuário específico.
     * <p>
     * Este endpoint permite a criação de um novo jogo associado a um usuário com base nos dados fornecidos no corpo da solicitação.
     *
     * @param idUsuario     O identificador do usuário ao qual o jogo será associado.
     * @param jogoCreateDTO Os dados do jogo a serem criados, especificados no corpo da solicitação no formato JSON.
     * @return ResponseEntity contendo uma instância de JogoDTO com os detalhes do jogo recém-criado.
     * @throws Exception Se ocorrer algum erro durante a criação do jogo, uma exceção pode ser lançada.
     */
    @PostMapping("{idUsuario}")
    public ResponseEntity<JogoDTO> criarJogo(@PathVariable("idUsuario") Integer idUsuario, @RequestBody JogoCreateDTO jogoCreateDTO) throws Exception {
        return ResponseEntity.ok(this.jogoService.criarJogo(idUsuario, jogoCreateDTO));
    }

    /**
     * Deleta um jogo pelo ID.
     *
     * @param id O ID do jogo a ser deletado.
     * @return ResponseEntity indicando que a solicitação foi aceita.
     */
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable("id") Integer id) {
        jogoService.deletarPorId(id);
        return ResponseEntity.accepted().build();
    }

    /**
     * Edita um jogo pelo ID.
     *
     * @param id            O ID do jogo a ser editado.
     * @param jogoUpdateDTO Dados atualizados do jogo.
     * @return ResponseEntity indicando que a solicitação foi processada com sucesso.
     * @throws Exception Se ocorrer um erro durante a edição do jogo.
     */
    @PutMapping("{id}")
    public ResponseEntity<Void> editaJogo(@PathVariable("id") Integer id,
                                          @RequestBody JogoUpdateDTO jogoUpdateDTO) throws Exception {
        jogoService.editaJogoPorId(id, jogoUpdateDTO);
        return ResponseEntity.noContent().build();
    }

}
