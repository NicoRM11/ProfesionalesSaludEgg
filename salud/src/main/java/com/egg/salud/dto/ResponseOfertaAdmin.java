package com.egg.salud.dto;

import java.sql.Timestamp;
import lombok.Data;

@Data
public class ResponseOfertaAdmin {
  
     private Long id;
     private String nombreGuest;
     private String apellidoGuest;
     private String obra_social;
     private String nombreProfesional;
     private String apellidoProfesional;
     private Timestamp start;
     private Timestamp end;
     private String localidad;
     private String consultorio;
     private String modalidad;
     private String especialidad;
     private Long telefono;
     private Boolean disponible;
     private Boolean estado;
}
