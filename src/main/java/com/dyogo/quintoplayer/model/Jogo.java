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
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_jogo", nullable = false)
    private Integer id;
    @Column
    private String descricao;
    @Column
    private String enderecoDoJogo;
    @Column
    private LocalDateTime dataEHorario;

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

}
