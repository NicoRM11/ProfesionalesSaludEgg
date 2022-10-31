
package com.egg.salud.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ResponseProfesionalDTO {
    
    private Long id;
    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nacionalidad;
    private Date fecha_nac;
    private String especialidad;
    private String matricula;
    private Integer dni;
    private String domicilio;
    private String urlFoto;
    private Boolean estado;

}
