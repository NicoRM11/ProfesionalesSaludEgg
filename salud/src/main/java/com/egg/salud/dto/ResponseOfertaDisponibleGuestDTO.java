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

    private Date fecha_turno;
    private Date hora_turno;
    private String localidad_consultorio;
    private String modalidad;
    private String ubicacion_consultorio;

    private String nombre_profesional;
    private String apellido_profesional;
    private Long telefono_consultorio;
    private String especialidad;

}
