/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import com.egg.salud.entidades.Guest;
import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseListaOfertaDTO {
    
    private Long id;
    private Guest guest;
    private Long telefono;

    private Timestamp start;
    private Timestamp end;
    private String localidad;
    private String modalidad;
    private String consultorio;
    private String especialidad;
    private Boolean disponible;
    
}
