package com.egg.salud.dto;

import java.sql.Timestamp;
import lombok.Data;


@Data
public class ResponseFicheroProfesionalDTO {
    private Long id;
    private String nombreProfesional;
    private String apellidoProfesional;
    private String especialidad;
    private Timestamp fechaConsulta;
    private String descripcion;
    
}
