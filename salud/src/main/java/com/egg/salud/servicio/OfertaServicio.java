
package com.egg.salud.servicio;
import com.egg.salud.dto.CrearOfertaDTO;

import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseListaOfertaDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaAdmin;
import com.egg.salud.dto.ResponseOfertaDisponibleGuestDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaGuestDTO;
import com.egg.salud.dto.ResponseOfertaProfesionalDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfertaServicio {
   
    public ResponseEntity<?> crearOfertaProfesional(String usuario,CrearOfertaDTO crearOfertaDto);
   
    public ResponseEntity<?> modificarOferta(Long id, RequestOfertaProfesionalDTO requestOfertaProfesionalDTO, String usuario);
    
    public ResponseEntity<?> eliminarOfertaProfesional(Long id, String usuario);


    
    public ResponseEntity<List<ResponseListaOfertaDTO>> todasLasOfertasProfesional(String usuario);
    
    public ResponseEntity<List<ResponseOfertaDisponibleProfesionalDTO>> buscarOfertaProfesionalDisponible();
    
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> todasLasOfertasGuest(String usuario);
    
    public ResponseEntity<?> aceptarOfertaGuest(String usuario,Long id);
    
    public ResponseEntity<?> cancelarOfertaGuest(String usuario,Long id);
               
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> buscarOfertaGuestAceptadas(String usuario);
    
    public ResponseEntity<List<ResponseOfertaDisponibleGuestDTO>> buscarOfertaGuestDisponible();
    
    public ResponseEntity<List<ResponseOfertaGuestDTO>> filtroBusqueda(String localidad , String especialidad);
    
    public ResponseEntity<List<ResponseOfertaProfesionalDTO>> ofertasProfesionalDisponibles(String usuario);
    
    public List<ResponseOfertaAdmin> listadoCompletoOfertas() throws Exception;
//public ResponseEntity<List<ResponseGuestDTO>> listarOfertaProfesional();
//    
//    
//    public ResponseEntity<?> aceptarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);
//    
//    public ResponseEntity<?> cancelarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);

    
    
    
    
}
