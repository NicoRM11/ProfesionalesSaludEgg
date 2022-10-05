package com.egg.salud.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "usuarios")
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String usuario;
    private String contraseña;
    private boolean estado;

    private Set<Rol> roles = new HashSet<>();

    public Usuario(Long id, String usuario, String contraseña, boolean estado, Set<Rol> roles) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.estado = estado;
        this.roles = roles;
    }

}
