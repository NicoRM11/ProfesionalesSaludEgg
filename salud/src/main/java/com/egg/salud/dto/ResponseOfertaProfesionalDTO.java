/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import com.egg.salud.entidades.Profesional;
import java.sql.Timestamp;
import lombok.Data;

@Data
public class ResponseOfertaProfesionalDTO {
    
    private Long id;
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
