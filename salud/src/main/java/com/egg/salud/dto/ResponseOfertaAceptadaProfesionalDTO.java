/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.egg.salud.entidades.Guest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Walter
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseOfertaAceptadaProfesionalDTO {
     private Guest guest;
     private String apellido_guest;
     private String obra_social_guest;
     private Long telefono_guest;
     private Date fecha_nac_guest;
     
     private Date fecha_turno;
     private Date hora_turno;
     private String localidad_consultorio;
     private String modalidad;
     private String ubicacion_consultorio;

     public ResponseOfertaAceptadaProfesionalDTO(Guest asd, String asd1, String asd2, String asd3, long l, Date date, Date date1, Date date2, String asd4, String asd5, String asd6) {
     }
}
