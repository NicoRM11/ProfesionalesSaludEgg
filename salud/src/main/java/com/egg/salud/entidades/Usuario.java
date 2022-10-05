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
    protected Long id;
    protected String usuario;
    protected String contraseña;
    protected Boolean estado;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "usuarios_roles", joinColumns = @JoinColumn(name = "usuario_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "rol_id", referencedColumnName = "id"))
    private Set<Rol> roles = new HashSet<>();

    public Usuario(Long id, String usuario, String contraseña, boolean estado, Set<Rol> roles) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.estado = estado;
        this.roles = roles;
    }

}
