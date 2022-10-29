/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.controladores;

import com.egg.salud.dto.RequestFicheroDTO;
import com.egg.salud.servicio.FicheroGuestServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author santi
 */




@RestController
@RequestMapping("/api/fichero")
@CrossOrigin(origins = "http://localhost:3000")
public class FichaGuestControlador {
    
    @Autowired
    private FicheroGuestServicio ficheroServicio;
    
    
    @PostMapping("/crear-ficha/{usuarioProfesional}/{usuarioGuest}")
    public ResponseEntity<?> crearFicha(@RequestBody RequestFicheroDTO request,@PathVariable (name= "usuarioGuest") String usuarioGuest,@PathVariable (name= "usuarioProfesional") String usuarioProfesional) throws Exception{  
        return ResponseEntity.ok().body(ficheroServicio.crearFichero(request, usuarioGuest, usuarioProfesional));
    }
    
    @DeleteMapping("/{usuario}/{id}")
    public ResponseEntity<?> eliminarFicha(@PathVariable(name= "usuario")String usuario,@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(ficheroServicio.eliminarFichero(usuario, id));
    }
    
    @GetMapping("/listaGuest/{usuario}/{especialidad}")
    public ResponseEntity<?> listarFicheroGuest(@PathVariable String usuario,@PathVariable String especialidad) throws Exception{
        return ResponseEntity.ok().body(ficheroServicio.listaFicheroGuest(usuario,especialidad));
    }

    @GetMapping("/lista/guest/{usuario}/{especialidad}")
    public ResponseEntity<?> listarFicheroGuestForProfesional(@PathVariable String usuario,@PathVariable String especialidad) throws Exception{
        return ResponseEntity.ok().body(ficheroServicio.listarFicheroGuestForProfesional(usuario,especialidad));
    }

    
}
