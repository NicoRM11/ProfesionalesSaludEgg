
package com.egg.salud.controladores;

import com.egg.salud.dto.RegistroProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.servicio.ProfesionalServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profesional")
@CrossOrigin(origins = "http://localhost:3000")
public class ProfesionalControlador {
    
    @Autowired
    private ProfesionalServicio profesionalServicio;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistroProfesionalDTO registroProfesionalDTO){

        return profesionalServicio.registrarUsuario(registroProfesionalDTO);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@RequestBody RequestProfesionalDTO requestProfesionalDTO , @PathVariable(name = "id")Long id){
        return profesionalServicio.modificarUsuario(id , requestProfesionalDTO);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id") Long id){
        return profesionalServicio.eliminarUsuario(id);
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<ResponseProfesionalDTO>> listar(){
        return profesionalServicio.listar();
    }

}
