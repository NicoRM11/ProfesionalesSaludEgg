
package com.egg.salud.servicio;
import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OfertaServicio {
    
    //Registrar Oferta deberia ser un boton que este dentro de la plataforma del profesional, no se si aplicaria un registro DTO
    public ResponseEntity<?> crearOfertaProfesional(CrearOfertaDTO crearOfertaDto);

    //Entiendo que el unico capaz de modificar la oferta es el profesional
    public ResponseEntity<?> modificarOfertaProfesional(Long idProfesional, RequestGuestDTO modificarDto);

    public ResponseEntity<?> eliminarOfertaProfesional(Long idProfesional);

    
    public ResponseEntity<List<ResponseGuestDTO>> listarOfertaProfesional();
    
    
    public ResponseEntity<?> aceptarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);
    
    public ResponseEntity<?> cancelarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto);

    
    
    
    
}
