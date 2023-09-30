package com.dyogo.quintoplayer.controller;

import com.dyogo.quintoplayer.dto.*;
import com.dyogo.quintoplayer.model.Avaliacao;
import com.dyogo.quintoplayer.service.AvaliacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/avaliacoes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class AvaliacaoController {

    private final AvaliacaoService avaliacaoService;


    /**
     * Cria uma nova avaliação para um usuário específico.
     * <p>
     * Este endpoint permite a criação de uma nova avaliação associada a um usuário com base nos dados fornecidos no corpo da solicitação.
     *
     * @param idUsuario          O identificador do usuário para o qual a avaliação será criada.
     * @param avaliacaoCreateDTO Os dados da avaliação a ser criada, especificados no corpo da solicitação no formato JSON.
     * @return ResponseEntity contendo uma instância de AvaliacaoDTO com os detalhes da avaliação recém-criada.
     * @throws Exception Se ocorrer algum erro durante a criação da avaliação, uma exceção pode ser lançada.
     */
    @PostMapping("/{idUsuario}")
    public ResponseEntity criarAvaliacao(@PathVariable("idUsuario") Integer idUsuario, @RequestBody AvaliacaoCreateDTO avaliacaoCreateDTO) throws Exception {
        return ResponseEntity.ok(this.avaliacaoService.criarAvaliacao(idUsuario, avaliacaoCreateDTO));
    }

    /**
     * Obtém todas as avaliações associadas a um usuário específico.
     * <p>
     * Este endpoint permite a recuperação de todas as avaliações que estão relacionadas a um usuário com base no seu identificador.
     *
     * @param idUsuario O identificador do usuário para o qual as avaliações serão obtidas.
     * @return ResponseEntity contendo uma lista de AvaliacaoDTOs com os detalhes das avaliações associadas ao usuário.
     * @throws Exception Se ocorrer algum erro durante a recuperação das avaliações, uma exceção pode ser lançada.
     */
    @GetMapping("/{idUsuario}")
    public ResponseEntity<List<AvaliacaoDTO>> obterAvaliacoesPorUsuario(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        return ResponseEntity.ok(avaliacaoService.obterAvaliacoesPorUsuario(idUsuario));
    }

    /**
     * Obtém todas as avaliações destinadas a um usuário específico.
     * <p>
     * Este endpoint permite a recuperação de todas as avaliações que foram destinadas a um usuário com base no seu identificador.
     *
     * @param idUsuario O identificador do usuário destinatário das avaliações.
     * @return ResponseEntity contendo uma lista de AvaliacaoDTOs com os detalhes das avaliações destinadas ao usuário.
     * @throws Exception Se ocorrer algum erro durante a recuperação das avaliações destinadas ao usuário, uma exceção pode ser lançada.
     */
    @GetMapping("destinado/{idUsuario}")
    public ResponseEntity<List<AvaliacaoDTO>> obterAvaliacoesPorUsuarioDestinado(@PathVariable("idUsuario") Integer idUsuario) throws Exception {
        return ResponseEntity.ok(avaliacaoService.obterAvaliacoesPorUsuarioDestinatario(idUsuario));
    }

}
