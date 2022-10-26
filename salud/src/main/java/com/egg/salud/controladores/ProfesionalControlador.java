
package com.egg.salud.controladores;



import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.servicio.OfertaServicio;
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
    
    @Autowired
    private OfertaServicio ofertaServicio;
    
    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody RequestProfesionalDTO requestProfesionalDTO) throws Exception{

        return ResponseEntity.ok().body(profesionalServicio.registrarUsuario(requestProfesionalDTO));
    }
    
    @PutMapping("/{usuario}")
    public ResponseEntity<?> modificar(@RequestBody RequestProfesionalDTO requestProfesionalDTO , @PathVariable(name = "usuario")String usuario) throws Exception{
        return ResponseEntity.ok().body(profesionalServicio.modificarUsuario(usuario, requestProfesionalDTO));
    }
    
    @DeleteMapping("/{usuario}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "usuario") String usuario) throws Exception{
        return ResponseEntity.ok().body(profesionalServicio.eliminarUsuario(usuario));
    }
    
    @GetMapping("/lista")
    public ResponseEntity<List<ResponseProfesionalDTO>> listar() throws Exception{
        return ResponseEntity.ok().body(profesionalServicio.listar());
    }

    @GetMapping("/detalle/{usuario}")
    public ResponseEntity<?>buscarPorEmail(@PathVariable String usuario) throws Exception{
        return ResponseEntity.ok().body(profesionalServicio.buscarPorEmail(usuario));
    }

    @GetMapping("/lista/{especialidad}")
    public ResponseEntity<List<ResponseProfesionalDTO>> listarEspecialidad(@PathVariable(name= "especialidad") String especialidad) throws Exception{
        return ResponseEntity.ok().body(profesionalServicio.listarEspecialidad(especialidad));
    }
    
    
    
    
}
