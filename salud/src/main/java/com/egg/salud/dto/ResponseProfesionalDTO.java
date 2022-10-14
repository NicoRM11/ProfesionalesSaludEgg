
package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class ResponseProfesionalDTO {

    private String nombre;
    private String apellido;
    private String usuario;
    private String nacionalidad;
    private Date fecha_nac;
    private List<String> especialidades = new ArrayList<>();
    private List<String> matriculas = new ArrayList<>();
    private String domicilio;
    private Boolean estado;
}
