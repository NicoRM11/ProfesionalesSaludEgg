/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import java.util.Date;

import com.egg.salud.entidades.Guest;
import java.sql.Timestamp;
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

    private Long id;
    private Guest guest;
    private String apellido;
    private String obra_social;
    private Long telefono;
    private Date fecha_nac;

    private Timestamp start;
    private Timestamp end;
    private String localidad;
    private String modalidad;
    private String consultorio;


}
