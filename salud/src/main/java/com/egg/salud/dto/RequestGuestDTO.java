package com.egg.salud.dto;

import lombok.Data;


@Data
public class RequestGuestDTO {

    private String nombre;
    private String apellido;
    private String usuario;
    private Integer dni;
    private String password;
    private String obra_social;
    private Long telefono;
    private String fecha_nac;
    private String nacionalidad;
    private String localidad;
    private String urlFoto;

}
