package com.egg.salud.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class ResponseFicheroGuestDTO {
    private Long id;
    private String nombreGuest;
    private String apellidoGuest;
    private String obra_social;
    private Integer dni;
    private Timestamp fechaConsulta;
    private String descripcion;
    
    
}
