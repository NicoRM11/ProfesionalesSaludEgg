
package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class RequestProfesionalDTO {

    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nacionalidad;
    private String fecha_nac;
    private String especialidad;
    private String matricula;
    private Integer dni;
    private String domicilio;
   
}
