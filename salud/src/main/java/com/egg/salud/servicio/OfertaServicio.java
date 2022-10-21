
package com.egg.salud.servicio;
import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfertaServicio {
   
    public ResponseEntity<?> crearOfertaProfesional(String profesional,CrearOfertaDTO crearOfertaDto);

    
//    public ResponseEntity<?> modificarOfertaProfesional(String usuario, RequestOfertaProfesionalDTO modificarDto);
//
//    public ResponseEntity<?> eliminarOfertaProfesional(Long idProfesional);
//
//    
//    public ResponseEntity<List<ResponseGuestDTO>> listarOfertaProfesional();
//    
//    
//    public ResponseEntity<?> aceptarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);
//    
//    public ResponseEntity<?> cancelarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);

    
    
    
    
}
