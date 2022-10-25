package com.egg.salud.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Walter
 */
@Data
public class CrearOfertaDTO {

   
    private String start;
    private String end;
    private String localidad;
    
    private String modalidad;
    
    private Long telefono;

    private String consultorio;
    
    
    

}
