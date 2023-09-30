package com.dyogo.quintoplayer.repository;

import com.dyogo.quintoplayer.model.Avaliacao;
import com.dyogo.quintoplayer.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Integer> {

    /**
     * Encontra avaliações pelo ID do usuário que as criou.
     *
     * @param idUsuario O ID do usuário cujas avaliações estão sendo buscadas.
     * @return Uma lista de avaliações associadas ao usuário com o ID especificado.
     */
    List<Avaliacao> findAvaliacaoByUsuario_Id(Integer idUsuario);

    /**
     * Encontra avaliações pelo ID do usuário destinatário.
     *
     * @param idUsuarioDestinatario O ID do usuário destinatário das avaliações.
     * @return Uma lista de avaliações destinadas ao usuário com o ID especificado.
     */
    List<Avaliacao> findAvaliacaoByUsuarioDestinado_Id(Integer idUsuarioDestinatario);
}
