
package com.egg.salud.entidades;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name="guest")
@NoArgsConstructor
public class Guest extends Usuario{

   private String email;
   private String nombre;
   private String apellido;
   private long dni;
   private String obra_social;
   private long telefono;
   
   @Temporal(TemporalType.DATE)
   private Date fecha_nac;
   
   private String nacionalidad;
   private String urlFoto;
   
    @OneToMany(mappedBy ="id_user", cascade=CascadeType.ALL)
    private List<Oferta> oferta;

    public Guest(String email, String nombre, String apellido, long dni, String obra_social, long telefono, Date fecha_nac, String nacionalidad, String urlFoto, Long id, String usuario, String contraseña, boolean estado, Set<Rol> roles) {
        super(id, usuario, contraseña, estado, roles);
        this.email = email;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.obra_social = obra_social;
        this.telefono = telefono;
        this.fecha_nac = fecha_nac;
        this.nacionalidad = nacionalidad;
        this.urlFoto = urlFoto;
    }

    

    
    
    
}
