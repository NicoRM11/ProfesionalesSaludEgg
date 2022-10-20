package com.egg.salud.servicio;

import com.egg.salud.dto.LoginDTO;
import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.dto.ResponseUsuarioDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioServicio {

    public ResponseEntity<?> registrarAdmin(RequestUsuarioDTO request);

    public ResponseEntity<?> modificarAdmin(String request, String usuario);

    public ResponseEntity<List<ResponseUsuarioDTO>> listarAdmin();

    public ResponseEntity<?> eliminarAdmin(String usuario);
    
    public ResponseEntity<?> login(LoginDTO login);
    
    public ResponseEntity<?> buscarUsuario(String usuario);
    
    public ResponseEntity<?> altaUsuario(String usuario);

}
