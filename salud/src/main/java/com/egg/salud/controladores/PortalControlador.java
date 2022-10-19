/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.controladores;

import com.egg.salud.dto.LoginDTO;
import com.egg.salud.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PortalControlador {
    
@Autowired
private UsuarioServicio usuarioServicio;


@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginDTO login){
    return usuarioServicio.login(login);
}


}
