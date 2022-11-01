/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.dto;

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

/**
 *
 * @author santi
 */
@Data
public class ResponseOfertaDisponibleGuestDTO {

    private Long id;
    private Timestamp start;
    private Timestamp end;
    private String localidad;
    private String modalidad;
    private String consultorio;

    private String nombre;
    private String apellido;
    private Long telefono;
    private String especialidad;
    private String nombreProfesional;
    private String apellidoProfesional;

}
