package com.egg.salud.dto;

import lombok.Data;

import java.util.Date;
@Data
public class ResponseGuestDTO  {
    
   private Long id;
   private String usuario;
   private String password;
   private String nombre;
   private String apellido;
   private String urlFoto;
   private Integer dni;
   private String obra_social;
   private long telefono;
   private Date fecha_nac;
   private String nacionalidad;
   private String localidad;
   private Boolean estado;
}
