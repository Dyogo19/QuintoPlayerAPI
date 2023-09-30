package com.dyogo.quintoplayer.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario", nullable = false)
    private Integer id;
    @Column
    private String nome;
    @Column
    private String email;
    @Column
    private String numeroDeCelular;
    @Column
    private String senha;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "usuario")
    private List<Avaliacao> avaliacaoList;

}
