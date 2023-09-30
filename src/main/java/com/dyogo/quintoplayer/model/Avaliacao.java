package com.dyogo.quintoplayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer id;
    @Column
    private String avaliacao;
    @Column
    private Integer nota;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuarioDestinatario", referencedColumnName = "id_usuario")
    private Usuario usuarioDestinado;

//    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private Usuario usuario;
}
