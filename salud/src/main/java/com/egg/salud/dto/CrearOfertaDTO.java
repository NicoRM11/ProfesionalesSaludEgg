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
    private String localidad_consultorio;
    
    private String modalidad;
    
    private Long telefono_consultorio;

    private String domicilio_consultorio;
    
    
    

}
