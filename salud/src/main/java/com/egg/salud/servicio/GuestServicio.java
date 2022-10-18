package com.egg.salud.servicio;


import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GuestServicio {

    public ResponseEntity<?> registrarUsuario(RegistroGuestDTO registroDto);

    public ResponseEntity<?> modificarUsuario(String usuario, RequestGuestDTO modificarDto);

    public ResponseEntity<?> eliminarUsuario(String usuario);

    public ResponseEntity<List<ResponseGuestDTO>> listar();

    public ResponseEntity<List<ResponseGuestDTO>> listarObraSocial(String obra_social); 
    
    public ResponseEntity<?> buscarPorEmail(String usuario);
    
}
