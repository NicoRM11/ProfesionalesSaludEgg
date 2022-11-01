package com.egg.salud.dto;

import com.egg.salud.entidades.Profesional;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class ResponseOfertaGuestDTO {
     
    private Long id;
    private Timestamp start;
    private Timestamp end;
    private String localidad;
    private String modalidad;
    private String consultorio;
    private Profesional profesional;
    private Long telefono;
    
}
