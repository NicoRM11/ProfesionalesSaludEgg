
package com.egg.salud.servicio;
import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfertaServicio {
   
    public ResponseEntity<?> crearOfertaProfesional(String usuario,CrearOfertaDTO crearOfertaDto);

    
    public ResponseEntity<?> modificarOferta(Long id, RequestOfertaProfesionalDTO requestOfertaProfesionalDTO);
    
    
    
    public ResponseEntity<?> eliminarOfertaProfesional(Long id);

    
    public ResponseEntity<List<ResponseOfertaAceptadaProfesionalDTO>> buscarOfertaProfesionalAceptadas();
    
//public ResponseEntity<List<ResponseGuestDTO>> listarOfertaProfesional();
//    
//    
//    public ResponseEntity<?> aceptarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);
//    
//    public ResponseEntity<?> cancelarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);

    
    
    
    
}
