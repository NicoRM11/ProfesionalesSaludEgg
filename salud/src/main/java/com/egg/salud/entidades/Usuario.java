package com.egg.salud.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String usuario;
    private String contraseña;
    private boolean estado;

    private String ssss;
    private Set<Rol> roles = new HashSet<>();
}
