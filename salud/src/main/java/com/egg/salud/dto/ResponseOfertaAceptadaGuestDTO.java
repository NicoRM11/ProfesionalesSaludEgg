/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Walter
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseOfertaAceptadaGuestDTO {

    private Long id;
    private String nombre;
    private String apellido;
    private Long telefono;
    private String especialidad;

    private Timestamp start;
    private Timestamp end;
    private String localidad;
    private String modalidad;
    private String consultorio;
    private String nombreProfesional;
    private String apellidoProfesional;
}
