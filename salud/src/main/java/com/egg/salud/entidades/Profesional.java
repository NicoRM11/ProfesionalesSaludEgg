package com.egg.salud.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "profesional")
@NoArgsConstructor
public class Profesional extends Usuario{

    private String nombre;
    private String apellido;
    private String urlFoto;
    private List<String> especialidades;
    private List<String> matriculas;
    private Integer dni;
    private String domicilio;

    public Profesional(Long id, String usuario, String contraseña, boolean estado, Set<Rol> roles, String nombre, String apellido, String urlFoto, List<String> especialidades, List<String> matriculas, Integer dni, String domicilio) {
        super(id, usuario, contraseña, estado, roles);
        this.nombre = nombre;
        this.apellido = apellido;
        this.urlFoto = urlFoto;
        this.especialidades = especialidades;
        this.matriculas = matriculas;
        this.dni = dni;
        this.domicilio = domicilio;
    }
}
