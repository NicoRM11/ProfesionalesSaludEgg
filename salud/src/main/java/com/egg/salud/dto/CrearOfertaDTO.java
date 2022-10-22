package com.egg.salud.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Walter
 */
@Data
public class CrearOfertaDTO {

   
    private Date hora;
    private Date fecha;
    private String localidad;
    
    private String modalidad;
    
    private Long telefono;
    
    
    

}
