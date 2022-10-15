package com.egg.salud.dto;

import lombok.Data;

import java.util.Date;

@Data
public class RequestGuestDTO {

    private String nombre;
    private String apellido;
    private String obra_social;
    private long telefono;
    private String fecha_nac;
    private String nacionalidad;
    private String localidad;

}
