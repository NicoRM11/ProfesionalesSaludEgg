package com.egg.salud.entidades;

import com.egg.salud.enumeraciones.Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Column(unique = true)
    protected String usuario;
    
    protected String password;
    protected Boolean estado;
    
    @Enumerated(EnumType.STRING)
    protected Rol rol;



}
