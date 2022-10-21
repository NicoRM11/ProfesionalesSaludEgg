package com.egg.salud.dto;

import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;

/**
 *
 * @author Walter
 */
@Data
public class CrearOfertaDTO {

   
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Temporal(TemporalType.TIME)
    private Date hora;
    private String localidad;
    
    private String modalidad;
    
    private Integer telefono;
    
    
    

}
