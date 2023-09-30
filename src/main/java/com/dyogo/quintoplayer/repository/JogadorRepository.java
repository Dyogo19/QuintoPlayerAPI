package com.dyogo.quintoplayer.repository;

import com.dyogo.quintoplayer.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface JogadorRepository extends JpaRepository<Jogador, Integer> {

}
