/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Walter
 */
public class ResponseOfertaProfesionalDTO {
     private String usuarioProfesional;
     private List<String> fechasDisponibles = new ArrayList<>();
    private List<String> HorariosDisponibles = new ArrayList<>();
    private List<String> UbicacionesDisponibles = new ArrayList<>();
}
