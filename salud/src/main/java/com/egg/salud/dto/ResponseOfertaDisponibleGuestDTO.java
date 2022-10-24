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
public class ResponseOfertaDisponibleGuestDTO {

    private Long id;
    private Date start;
    private Date end;
    private String localidad;
    private String modalidad;
    private String consultorio;

    private String nombre;
    private String apellido;
    private Long telefono;
    private String especialidad;

}
