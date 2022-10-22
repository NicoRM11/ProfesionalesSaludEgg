/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.controladores;

import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.servicio.GuestServicio;
import com.egg.salud.servicio.OfertaServicio;
import com.egg.salud.servicio.ProfesionalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Walter
 */
@RestController
@RequestMapping("/api/oferta")
@CrossOrigin(origins = "http://localhost:3000")
public class OfertaControlador {

    @Autowired
    private ProfesionalServicio profesionalServicio;

    @Autowired
    private OfertaServicio ofertaServicio;

    @Autowired
    private GuestServicio guestServicio;
    
    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarOferta(@RequestBody RequestOfertaProfesionalDTO requestOfertaProfesionalDTO , @PathVariable(name = "id")Long id){
        return ofertaServicio.modificarOferta(id , requestOfertaProfesionalDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarOfertaProfesional(@PathVariable(name = "id") Long id){
        return ofertaServicio.eliminarOfertaProfesional(id);
    }
    
    @GetMapping("/ofertas-aceptadas-vista-profesional")
    public ResponseEntity<?> buscarOfertaProfesionalAceptadas(){
        return ofertaServicio.buscarOfertaProfesionalAceptadas();
    }

    
    

}
