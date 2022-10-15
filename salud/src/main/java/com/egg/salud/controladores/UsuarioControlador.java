package com.egg.salud.controladores;

import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/all")
    public ResponseEntity<?> listar(){
        return usuarioServicio.listarAdmin();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RequestUsuarioDTO request){
        return usuarioServicio.registrarAdmin(request);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@RequestBody String request , @PathVariable Long id){
        return usuarioServicio.modificarAdmin(request,id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id){
        return usuarioServicio.eliminarAdmin(id);
    }


}
