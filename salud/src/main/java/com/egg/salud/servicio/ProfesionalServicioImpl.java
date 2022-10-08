
package com.egg.salud.servicio;

import com.egg.salud.dto.RegistroProfesionalDTO;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import com.egg.salud.repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProfesionalServicioImpl implements ProfesionalServicio{
    
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroProfesionalDTO registroDto) {
        
        
        
        
        
        
        
        
       return new ResponseEntity<>("Usuario registrado exitosamente" , HttpStatus.OK);
    }

}
