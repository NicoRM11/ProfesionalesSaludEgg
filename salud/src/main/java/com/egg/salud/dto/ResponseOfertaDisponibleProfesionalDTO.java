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

import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;

@Data
public class ResponseOfertaDisponibleProfesionalDTO {
    
    
    private Long id;
    private Timestamp start;
     private Timestamp end;
     private String localidad;
     private String modalidad;
     private String consultorio;
    
    
}
