/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import com.egg.salud.entidades.Guest;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListaOfertaDTO {
    
    private Long id;
    //private Guest guest;
    private Long telefono;
    private String nombre;
    private String apellido;
    private Long telefonoGuest;

    private Date start;
    private Date end;
    private String localidad;
    private String modalidad;
    private String consultorio;
    private String especialidad;
    private Boolean disponible;
    
}
