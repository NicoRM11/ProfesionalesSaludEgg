package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RegistroProfesionalDTO {

    private String nombre;
    private String apellido;
    private String usuario;
    private String password;
    private String nacionalidad;
    private String fecha_nac;
    private List<String> especialidades = new ArrayList<>();
    private List<String> matriculas = new ArrayList<>();
    private Integer dni;
    private String domicilio;
   
}