package com.dyogo.quintoplayer.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Login {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_login", nullable = false)
    private Integer id;
    @Column
    private String email;
    @Column
    private String senha;
    @Column
    private String nome;
    @Column
    private String numeroDeCelular;

}


