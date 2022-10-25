
package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class RequestOfertaProfesionalDTO {
    
    private String start;
    private String end;
    private String localidad;
    //agregar - al imp
    private String consultorio;
    
    private String modalidad;
    
    private Long telefono;
    
    

    
    
}
   

