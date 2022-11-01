package com.egg.salud.controladores;

import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.servicio.GuestServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/guest")
@CrossOrigin(origins = "http://localhost:3000")
public class GuestControlador {
    
    @Autowired
    private GuestServicio guestServicio;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RequestGuestDTO requestGuestDto) throws Exception{

        return ResponseEntity.ok().body(guestServicio.registrarUsuario(requestGuestDto));
    }

    @GetMapping("/lista")
    public ResponseEntity<List<ResponseGuestDTO>> listar() throws Exception{
        return ResponseEntity.ok().body(guestServicio.listar());
    }

    @PutMapping("/{usuario}")
    public ResponseEntity<?> modificar(@RequestBody RequestGuestDTO requestGuestDTO , @PathVariable(name = "usuario")String usuario)
           throws Exception {
        return ResponseEntity.ok().body(guestServicio.modificarUsuario(usuario, requestGuestDTO));
    }

    @DeleteMapping("/{usuario}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "usuario") String usuario) throws Exception{
        return ResponseEntity.ok().body(guestServicio.eliminarUsuario(usuario));
    }
    
    @GetMapping("/detalle/{usuario}")
    public ResponseEntity<?> buscarPorEmail(@PathVariable String usuario) throws Exception{
        return ResponseEntity.ok().body(guestServicio.buscarPorEmail(usuario));
    }

    @GetMapping("/lista/{obra_social}")
    public ResponseEntity<List<ResponseGuestDTO>> listarObraSocial(@PathVariable(name= "obra_social") String obra_social) throws Exception{
        return ResponseEntity.ok().body(guestServicio.listarObraSocial(obra_social));
    }


    
}
