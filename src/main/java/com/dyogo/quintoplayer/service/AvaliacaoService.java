package com.dyogo.quintoplayer.service;

import com.dyogo.quintoplayer.dto.AvaliacaoCreateDTO;
import com.dyogo.quintoplayer.dto.AvaliacaoDTO;
import com.dyogo.quintoplayer.dto.JogadorCreateDTO;
import com.dyogo.quintoplayer.dto.JogadorDTO;
import com.dyogo.quintoplayer.model.Avaliacao;
import com.dyogo.quintoplayer.model.Jogador;
import com.dyogo.quintoplayer.model.Usuario;
import com.dyogo.quintoplayer.repository.AvaliacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AvaliacaoService {

    private final AvaliacaoRepository avaliacaoRepository;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    public AvaliacaoService(AvaliacaoRepository avaliacaoRepository, UsuarioService usuarioService, ObjectMapper objectMapper) {
        this.avaliacaoRepository = avaliacaoRepository;
        this.usuarioService = usuarioService;
        this.objectMapper = objectMapper;
    }

    /**
     * Cria uma nova avaliação.
     *
     * @param idUsuario          O identificador do usuário que está criando a avaliação.
     * @param avaliacaoCreateDTO Dados da avaliação a ser criada.
     * @return Uma instância de AvaliacaoDTO contendo os detalhes da avaliação recém-criada.
     * @throws Exception Se ocorrer algum erro durante a criação da avaliação.
     */
    public AvaliacaoDTO criarAvaliacao(Integer idUsuario, AvaliacaoCreateDTO avaliacaoCreateDTO) throws Exception {
        Avaliacao avaliacao = objectMapper.convertValue(avaliacaoCreateDTO, Avaliacao.class);
        avaliacao.setUsuario(usuarioService.buscarPorId(idUsuario));
        avaliacao.setUsuarioDestinado(usuarioService.buscarPorId(avaliacaoCreateDTO.idUsuarioDestinatario()));
        AvaliacaoDTO avaliacaoDTO = objectMapper.convertValue(avaliacaoRepository.save(avaliacao), AvaliacaoDTO.class);
        return avaliacaoDTO;
    }

    /**
     * Obtém todas as avaliações associadas a um usuário específico.
     *
     * @param idUsuario O identificador do usuário para o qual as avaliações serão obtidas.
     * @return Uma lista de AvaliacaoDTOs com os detalhes das avaliações associadas ao usuário.
     * @throws Exception Se ocorrer algum erro durante a recuperação das avaliações.
     */

    public List<AvaliacaoDTO> obterAvaliacoesPorUsuario(Integer idUsuario) throws Exception {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        List<Avaliacao> avaliacaoByUsuarioDestinadoId = avaliacaoRepository.findAvaliacaoByUsuarioDestinado_Id(usuario.getId());
        return avaliacaoByUsuarioDestinadoId.stream()
                .map(avaliacao -> objectMapper.convertValue(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList());

    }

    /**
     * Obtém todas as avaliações destinadas a um usuário específico.
     *
     * @param idUsuario O identificador do usuário destinatário das avaliações.
     * @return Uma lista de AvaliacaoDTOs com os detalhes das avaliações destinadas ao usuário.
     * @throws Exception Se ocorrer algum erro durante a recuperação das avaliações destinadas ao usuário.
     */

    public List<AvaliacaoDTO> obterAvaliacoesPorUsuarioDestinatario(Integer idUsuario) throws Exception {
        Usuario usuario = usuarioService.buscarPorId(idUsuario);

        List<Avaliacao> avaliacaoByUsuarioDestinadoId = avaliacaoRepository.findAvaliacaoByUsuario_Id(usuario.getId());
        return avaliacaoByUsuarioDestinadoId.stream()
                .map(avaliacao -> objectMapper.convertValue(avaliacao, AvaliacaoDTO.class))
                .collect(Collectors.toList());

    }

    /**
     * Obtém todas as avaliações existentes.
     *
     * @return Uma lista de objetos Avaliacao representando todas as avaliações.
     */
    public List<Avaliacao> obterTodasAsAvaliacoes() {
        return avaliacaoRepository.findAll();
    }

}