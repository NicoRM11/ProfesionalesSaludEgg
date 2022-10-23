/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author Walter
 */
@Data
public class ResponseOfertaAceptadaGuestDTO {

    private String nombre_profesional;
    private String apellido_profesional;
    private Long telefono_consultorio;
    private String especialidad;

    private Date fecha_turno;
    private Date hora_turno;
    private String localidad_consultorio;
    private String modalidad;
    private String ubicacion_consultorio;

}
