package com.egg.salud.entidades;

import com.egg.salud.enumeraciones.Rol;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "profesional")
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Profesional extends Usuario {

    
    private String nombre;
    private String apellido;
    private String urlFoto;
    private String nacionalidad;
    

    private String especialidad;

    private String matricula;
    
    private Integer dni;

    @Temporal(TemporalType.DATE)
    private Date fecha_nac;

    private String domicilio;

    @OneToMany(mappedBy = "id_profesional", cascade = CascadeType.ALL)
    private List<Oferta> oferta;

    public Profesional(Long id, String usuario, String password, Boolean estado, Rol rol, String nombre, String apellido, String urlFoto, String nacionalidad, String especialidad, String matricula, Integer dni, Date fecha_nac, String domicilio, List<Oferta> oferta) {
        super(id, usuario, password, estado, rol);
        this.nombre = nombre;
        this.apellido = apellido;
        this.urlFoto = urlFoto;
        this.nacionalidad = nacionalidad;
        this.especialidad = especialidad;
        this.matricula = matricula;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.domicilio = domicilio;
        this.oferta = oferta;
    }
}
