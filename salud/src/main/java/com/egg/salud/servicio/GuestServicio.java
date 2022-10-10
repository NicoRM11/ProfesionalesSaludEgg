package com.egg.salud.servicio;


import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GuestServicio {

    public ResponseEntity<?> registrarUsuario(RegistroGuestDTO registroDto);

    public ResponseEntity<?> modificarUsuario(Long idUsuario, RequestGuestDTO modificarDto);

    public ResponseEntity<?> eliminarUsuario(Long idUsuario);

    public ResponseEntity<List<ResponseGuestDTO>> listar();

}
