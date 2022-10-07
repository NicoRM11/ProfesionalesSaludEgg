/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.controladores;

import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.servicio.GuestServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/auth")
public class AuthControlador {
    
    @Autowired
    private GuestServicio guestServicio;
    
    @PostMapping("/registrarGuest")
    public ResponseEntity<?> registrar(@RequestBody RegistroGuestDTO registroGuestDto){
        
        
        
        return guestServicio.registrarUsuario(registroGuestDto);
    }
    
    
}
