
package com.egg.salud.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class RequestOfertaProfesionalDTO {
/*
   Para la creacion del objeto oferta se solicitara la informacion de login del profesional, 
    mas su disponibilidad de dias, horarios y ubicaciones.
    La informacion de especialidad ya viene con su login de usuario
    
    Otra opcion: que el profesional dentro de su visualizacion tenga un boton para cargar su oferta, donde el ya estaria
    logeado
    
    */
    //private String nombreProfesional;
    //private String apellidoProfesional;
    
    private String usuarioProfesional;
    private String passwordProfesional;
    
    /*Se deberia crear un objeto nuevo llamado Oferta en donde cada vez que el profesional eliga un dia o rango de dias,
    un horario, un horario o rango de horarios y ubicacion o ubicaciones?
*/
    private List<String> fechasDisponibles = new ArrayList<>();
    private List<String> HorariosDisponibles = new ArrayList<>();
    private List<String> UbicacionesDisponibles = new ArrayList<>();
    
}
   


