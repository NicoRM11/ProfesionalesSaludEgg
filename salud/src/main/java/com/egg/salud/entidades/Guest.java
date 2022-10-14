
package com.egg.salud.entidades;

import java.util.Date;
import java.util.List;
import java.util.Set;
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
   
   private Integer dni;
   
   private String obra_social;
   private long telefono;
   
   @Temporal(TemporalType.DATE)
   private Date fecha_nac;
   
   private String nacionalidad;
   private String urlFoto;
   private String localidad;
   
    @OneToMany(mappedBy ="id_user", cascade=CascadeType.ALL)
    private List<Oferta> oferta;

    public Guest(String nombre, String apellido, Integer dni, String obra_social, long telefono, Date fecha_nac, String nacionalidad, String urlFoto, String localidad, Long id, String usuario, String contraseña, Boolean estado, Set<Rol> roles) {
        super(id, usuario, contraseña, estado, roles);
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.obra_social = obra_social;
        this.telefono = telefono;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.urlFoto = urlFoto;
        this.localidad=localidad;
    }

    

    
    
    
}
