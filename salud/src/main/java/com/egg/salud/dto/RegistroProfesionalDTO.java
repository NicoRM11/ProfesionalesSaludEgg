package com.egg.salud.dto;

import com.egg.salud.entidades.Oferta;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class RegistroProfesionalDTO {

    private String usuario;
    private String password;
    private String urlFoto;
    private List<String> especialidades = new ArrayList<>();
    private List<String> matriculas = new ArrayList<>();
    private Integer dni;
    private String domicilio;
    private List<Oferta> oferta;
}
