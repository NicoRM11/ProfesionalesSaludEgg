/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author santi
 */
@Data
public class RegistroGuestDTO {

   private String usuario;
   private String contraseña;
   private String email;
   private String nombre;
   private String apellido;
   private long dni;
   private String obra_social;
   private long telefono;
   private Date fecha_nacimiento;
   private String nacionalidad;
   private String url_foto;

}
