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
    
    @ElementCollection
    private List<String> especialidades = new ArrayList<>();
    
    @ElementCollection
    private List<String> matriculas = new ArrayList<>();
    
    private Integer dni;

    @Temporal(TemporalType.DATE)
    private Date fecha_nac;

    private String domicilio;

    @OneToMany(mappedBy = "id_profesional", cascade = CascadeType.ALL)
    private List<Oferta> oferta;

    public Profesional(String nombre, String apellido, String urlFoto, String nacionalidad, Integer dni, Date fecha_nac, String domicilio, List<Oferta> oferta, Long id, String usuario, String password, Boolean estado, Rol rol) {
        super(id, usuario, password, estado, rol);
        this.nombre = nombre;
        this.apellido = apellido;
        this.urlFoto = urlFoto;
        this.nacionalidad = nacionalidad;
        this.dni = dni;
        this.fecha_nac = fecha_nac;
        this.domicilio = domicilio;
        this.oferta = oferta;
    }

   

    
    

    
}
