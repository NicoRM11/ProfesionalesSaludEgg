package com.egg.salud.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Walter
 */
@Data
public class CrearOfertaDTO {

   
    private Date start;
    private Date end;
    private String localidad;
    
    private String modalidad;
    
    private Long telefono;

    private String consultorio;
    
    
    

}
