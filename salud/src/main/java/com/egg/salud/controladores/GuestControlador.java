/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.controladores;

import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.servicio.GuestServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;


@RestController
@RequestMapping("/api/guest")
@CrossOrigin(origins = "http://localhost:3000")
public class GuestControlador {
    
    @Autowired
    private GuestServicio guestServicio;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistroGuestDTO registroGuestDto){

        return guestServicio.registrarUsuario(registroGuestDto);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/lista")
    public ResponseEntity<List<ResponseGuestDTO>> listar(){
        return guestServicio.listar();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@RequestBody RequestGuestDTO requestGuestDTO , @PathVariable(name = "id")Long id){
        return guestServicio.modificarUsuario(id , requestGuestDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id") Long id){
        return guestServicio.eliminarUsuario(id);
    }

    @GetMapping("/listar/{obra_social}")
    public ResponseEntity<List<ResponseGuestDTO>> listarObraSocial(@PathVariable(name= "obra_social") String obra_social){
        return guestServicio.listarObraSocial(obra_social);
    }


    
}
