package com.egg.salud.dto;

import java.sql.Timestamp;

import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Profesional;
import lombok.Data;

@Data
public class ResponseOfertaAdmin {
  
     private Long id;
     private Guest guest;
     private Profesional profesional;
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
