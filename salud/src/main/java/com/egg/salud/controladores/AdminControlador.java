package com.egg.salud.controladores;

import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.servicio.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @GetMapping("/lista")
    public ResponseEntity<?> listar(){
        return usuarioServicio.listarAdmin();
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RequestUsuarioDTO request){
        return usuarioServicio.registrarAdmin(request);
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<?> modificar(@RequestBody String request , @PathVariable String usuario){
        return usuarioServicio.modificarAdmin(request,usuario);
    }

    @DeleteMapping("/{usuario}")
    public ResponseEntity<?> eliminar(@PathVariable String usuario){
        return usuarioServicio.eliminarAdmin(usuario);
    }
    
}
