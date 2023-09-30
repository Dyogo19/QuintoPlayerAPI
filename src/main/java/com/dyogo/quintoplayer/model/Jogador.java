package com.dyogo.quintoplayer.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jogador", nullable = false)
    private Integer id;
    @Column
    private String nomeJogador;
    @Column
    private LocalDateTime dataEHoraLivrePraJogar;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
