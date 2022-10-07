package com.egg.salud.servicio;


import com.egg.salud.dto.RegistroGuestDTO;
import org.springframework.http.ResponseEntity;

public interface GuestServicio {

    public ResponseEntity<?> registrarUsuario(RegistroGuestDTO registroDto);


}
