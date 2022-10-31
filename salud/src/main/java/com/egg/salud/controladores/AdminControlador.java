package com.egg.salud.controladores;

import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.dto.ResponseUsuarioDTO;
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
        return ResponseEntity.ok().body(usuarioServicio.listarAdmin());
    }
    
    @GetMapping("/listaUsuarios")
    public ResponseEntity<?> listaUsuarios() throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.listarGuest());
    }

    @GetMapping("/listaProfesionales")
    public ResponseEntity<?> listaProfesionales() throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.listarProfesional());
    }
    
    @GetMapping("/listaCompleta")
    public ResponseEntity<?> listaCompletaUsuarios() throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.listaUsuariosCompleta());
    }
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RequestUsuarioDTO request) throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.registrarAdmin(request));
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<?> modificar(@RequestBody String request , @PathVariable String usuario) throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.modificarAdmin(request, usuario));
    }

    @DeleteMapping("/{usuario}")
    public ResponseEntity<?> eliminar(@PathVariable String usuario) throws Exception{
        return ResponseEntity.ok().body(usuarioServicio.eliminarAdmin(usuario));
    }
    
    @PatchMapping("/{usuario}")
    public ResponseEntity<?> altaUsuario(@PathVariable String usuario) throws Exception{
       return ResponseEntity.ok().body(usuarioServicio.altaUsuario(usuario));
    }
    
    @GetMapping("/detalle/{usuario}")
    public ResponseEntity<ResponseUsuarioDTO> buscarUsuario(@PathVariable String usuario) throws Exception{
       return ResponseEntity.ok().body(usuarioServicio.buscarUsuario(usuario));
    }
    
    
}
