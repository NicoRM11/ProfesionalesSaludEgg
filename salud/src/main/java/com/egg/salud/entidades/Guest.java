
package com.egg.salud.entidades;

import com.egg.salud.enumeraciones.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="guest")
@Data
@NoArgsConstructor
@PrimaryKeyJoinColumn(referencedColumnName = "id")
public class Guest extends Usuario{

   private String nombre;
   private String apellido;
   
   //hacer el DNI unique
   private Integer dni;
   
   private String obra_social;
   private Long telefono;
   
   @Temporal(TemporalType.DATE)
   private Date fecha_nac;
   
   private String nacionalidad;
   private String urlFoto;
   private String localidad;
   
   //id_guest?
    @OneToMany(mappedBy ="guest", cascade=CascadeType.ALL)
    private List<Oferta> oferta;

    public Guest(String nombre, String apellido, Integer dni, String obra_social, long telefono, Date fecha_nac, String nacionalidad, String urlFoto, String localidad, List<Oferta> oferta, Long id, String usuario, String password, Boolean estado, Rol rol) {
        super(id, usuario, password, estado, rol);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.obra_social = obra_social;
        this.telefono = telefono;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.urlFoto = urlFoto;
        this.localidad = localidad;
        this.oferta = oferta;
    }
    
}
