
package com.egg.salud.controladores;

import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RegistroProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.servicio.OfertaServicio;
import com.egg.salud.servicio.ProfesionalServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    
    @Autowired
    private OfertaServicio ofertaServicio;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RegistroProfesionalDTO registroProfesionalDTO){

        return profesionalServicio.registrarUsuario(registroProfesionalDTO);
    }
    
    @PutMapping("/{usuario}")
    public ResponseEntity<?> modificar(@RequestBody RequestProfesionalDTO requestProfesionalDTO , @PathVariable(name = "usuario")String usuario){
        return profesionalServicio.modificarUsuario(usuario , requestProfesionalDTO);
    }
    
    @DeleteMapping("/{usuario}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "usuario") String id){
        return profesionalServicio.eliminarUsuario(id);
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<ResponseProfesionalDTO>> listar(){
        return profesionalServicio.listar();
    }

    @GetMapping("/{usuario}")
    public ResponseEntity<?>buscarPorEmail(@PathVariable String usuario){
        return profesionalServicio.buscarPorEmail(usuario);
    }

    @GetMapping("/listar/{especialidad}")
    public ResponseEntity<List<ResponseProfesionalDTO>> listarEspecialidad(@PathVariable(name= "especialidad") String especialidad){
        return profesionalServicio.listarEspecialidad(especialidad);
    }
    
    
    
    @PostMapping("/crear-oferta/{usuario}")
    public ResponseEntity<?> crearOferta(@RequestBody CrearOfertaDTO crearOfertaDto,@PathVariable (name= "usuario") String usuario){
        System.out.println("mostrar (controlador) " + crearOfertaDto);
        return ofertaServicio.crearOfertaProfesional(usuario, crearOfertaDto);
                
                
    }
}
