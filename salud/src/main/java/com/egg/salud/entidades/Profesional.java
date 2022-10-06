package com.egg.salud.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity(name = "profesional")
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Profesional extends Usuario{

    private String nombre;
    private String apellido;
    private String urlFoto;
    @ElementCollection
    private List<String> especialidades = new ArrayList<>();
    @ElementCollection
    private List<String> matriculas = new ArrayList<>();
    private Integer dni;
    private String domicilio;

    @OneToMany(mappedBy = "id_profesional", cascade = CascadeType.ALL)
    private List<Oferta> oferta;

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
