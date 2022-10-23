/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.egg.salud.controladores;

import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleProfesionalDTO;
import com.egg.salud.servicio.GuestServicio;
import com.egg.salud.servicio.OfertaServicio;
import com.egg.salud.servicio.ProfesionalServicio;
import java.util.List;
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
    
    @GetMapping("/listar-ofertas-profesional/{usuario}")
    public ResponseEntity<List<ResponseOfertaAceptadaProfesionalDTO>> buscarOfertaProfesionalAceptadas(@PathVariable(name = "usuario") String usuario){
        return ofertaServicio.buscarOfertaProfesionalAceptadas(usuario);
    }
    
    @GetMapping("/listar-ofertas-profesional-disponibles")
    public ResponseEntity<List<ResponseOfertaDisponibleProfesionalDTO>> buscarOfertaProfesionalDisponibles(){
        return ofertaServicio.buscarOfertaProfesionalDisponible();
    }
    
    
//Revisar si es put o post o algun otro
    @PutMapping("/aceptar-oferta-guest/{usuario}/{id_oferta}")
    public ResponseEntity<?> aceptarOfertaGuest(@PathVariable(name = "id_oferta") Long id,@PathVariable(name = "usuario") String usuario){
        return ofertaServicio.aceptarOfertaGuest(usuario, id);
    }
    
    @DeleteMapping("/cancelar-oferta-guest/{usuario}/{id_oferta}")
    public ResponseEntity<?> cancelarOfertaGuest(@PathVariable(name = "id_oferta") Long id,@PathVariable(name = "usuario") String usuario){
        return ofertaServicio.cancelarOfertaGuest(usuario, id);
    }
    
    @GetMapping("/listar-ofertas-guest")
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> buscarOfertaGuestAceptadas(){
        return ofertaServicio.buscarOfertaGuestAceptadas();
    }

}
