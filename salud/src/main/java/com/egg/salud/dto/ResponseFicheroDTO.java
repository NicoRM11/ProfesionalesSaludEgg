package com.egg.salud.dto;

import com.egg.salud.entidades.Guest;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class ResponseFicheroDTO {
   
    private Guest guest;
    private Timestamp fechaConsulta;
    private String descripcion;
    
    
    
    
    
    
}
