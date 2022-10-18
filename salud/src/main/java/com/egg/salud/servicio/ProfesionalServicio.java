package com.egg.salud.servicio;

import com.egg.salud.dto.RegistroProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ProfesionalServicio {

    public ResponseEntity<?> registrarUsuario(RegistroProfesionalDTO registroDto);

    public ResponseEntity<?> modificarUsuario(String usuario, RequestProfesionalDTO modificarDto);

    public ResponseEntity<?> eliminarUsuario(String usuario);

    public ResponseEntity<List<ResponseProfesionalDTO>> listar();
    
    public ResponseEntity<?> buscarPorEmail(String usuario);

}
