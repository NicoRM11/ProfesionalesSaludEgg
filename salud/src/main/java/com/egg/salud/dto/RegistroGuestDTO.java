/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.dto;

import lombok.Data;

/**
 *
 * @author santi
 */
@Data
public class RegistroGuestDTO {

   private String usuario;
   private String password;
   private String nombre;
   private String apellido;
   private Integer dni;
   private String obra_social;
   private long telefono;
   private String fecha_nac;
   private String nacionalidad;
   private String localidad;

}
