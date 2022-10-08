
package com.egg.salud.servicio;

import com.egg.salud.dto.RegistroProfesionalDTO;
import org.springframework.http.ResponseEntity;


public interface ProfesionalServicio {
    
     public ResponseEntity<?> registrarUsuario(RegistroProfesionalDTO registroDto);

}
