
package com.egg.salud.servicio;
import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleGuestDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleProfesionalDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfertaServicio {
   
    public ResponseEntity<?> crearOfertaProfesional(String usuario,CrearOfertaDTO crearOfertaDto);

    
    public ResponseEntity<?> modificarOferta(Long id, RequestOfertaProfesionalDTO requestOfertaProfesionalDTO, String usuario);
    
    
    
    public ResponseEntity<?> eliminarOfertaProfesional(Long id, String usuario);

    
    public ResponseEntity<List<ResponseOfertaAceptadaProfesionalDTO>> buscarOfertaProfesionalAceptadas(String usuario);
    
    
    public ResponseEntity<List<ResponseOfertaDisponibleProfesionalDTO>> buscarOfertaProfesionalDisponible();
    
    
    public ResponseEntity<?> aceptarOfertaGuest(String usuario,Long id);
    
    
    public ResponseEntity<?> cancelarOfertaGuest(String usuario,Long id);
    
    
               
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> buscarOfertaGuestAceptadas(String usuario);
    
    
    public ResponseEntity<List<ResponseOfertaDisponibleGuestDTO>> buscarOfertaGuestDisponible();
//public ResponseEntity<List<ResponseGuestDTO>> listarOfertaProfesional();
//    
//    
//    public ResponseEntity<?> aceptarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);
//    
//    public ResponseEntity<?> cancelarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);

    
    
    
    
}