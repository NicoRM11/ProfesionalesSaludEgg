package com.egg.salud.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class RequestFicheroDTO {
    
    private String descripcion;
    private Timestamp fechaConsulta;

    
     
}
