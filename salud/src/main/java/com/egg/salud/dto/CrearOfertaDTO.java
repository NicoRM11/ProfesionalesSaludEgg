package com.egg.salud.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author Walter
 */
@Data
public class CrearOfertaDTO {

   
    private Timestamp start;
    private Timestamp end;
    private String localidad;
    
    private String modalidad;
    
    private Long telefono;

    private String consultorio;
    
    
    

}
