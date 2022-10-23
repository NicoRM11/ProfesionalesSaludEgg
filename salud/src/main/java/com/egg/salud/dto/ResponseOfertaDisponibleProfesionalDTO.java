/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

/**
 *
 * @author Walter
 */

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ResponseOfertaDisponibleProfesionalDTO {
    
    
    
    private Date fecha_turno;
     private Date hora_turno;
     private String localidad_consultorio;
     private String modalidad;
     private String ubicacion_consultorio;
    
    
}
