
package com.egg.salud.controladores;

import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseListaOfertaDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleGuestDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaGuestDTO;
import com.egg.salud.dto.ResponseOfertaProfesionalDTO;
import com.egg.salud.servicio.OfertaServicio;
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

/**
 *
 * @author Walter
 */
@RestController
@RequestMapping("/api/oferta")
@CrossOrigin(origins = "http://localhost:3000")
public class OfertaControlador {

//    @Autowired
//    private ProfesionalServicio profesionalServicio;

    @Autowired
    private OfertaServicio ofertaServicio;

//    @Autowired
//    private GuestServicio guestServicio;
    
    @PostMapping("/crear-oferta/{usuario}")
    public ResponseEntity<?> crearOferta(@RequestBody CrearOfertaDTO crearOfertaDto,@PathVariable (name= "usuario") String usuario){
       
        return ofertaServicio.crearOfertaProfesional(usuario, crearOfertaDto);
    }
    
    @PutMapping("/{usuario}/{id}")
    public ResponseEntity<?> modificarOferta(@RequestBody RequestOfertaProfesionalDTO requestOfertaProfesionalDTO , @PathVariable(name = "id")Long id,@PathVariable (name= "usuario") String usuario){
        return ofertaServicio.modificarOferta(id , requestOfertaProfesionalDTO, usuario);
    }
    
    @DeleteMapping("/{usuario}/{id}")
    public ResponseEntity<?> eliminarOfertaProfesional(@PathVariable(name = "id") Long id,@PathVariable (name= "usuario") String usuario){
        return ofertaServicio.eliminarOfertaProfesional(id, usuario);
    }
    
    @GetMapping("/listarOfertas")
    public ResponseEntity<?> listadoCompletoOfertas() throws Exception{
       return ResponseEntity.ok().body(ofertaServicio.listadoCompletoOfertas());
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
    
    @GetMapping("/listar-ofertas-guest/{usuario}")
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> buscarOfertaGuestAceptadas(@PathVariable(name = "usuario") String usuario){
        return ofertaServicio.buscarOfertaGuestAceptadas(usuario);
    }
    
    @GetMapping("/listar-ofertas-guest-disponibles")
    public ResponseEntity<List<ResponseOfertaDisponibleGuestDTO>> buscarOfertaGuestDisponibles(){
        return ofertaServicio.buscarOfertaGuestDisponible();
    }

    @GetMapping("/listar-todas-las-ofertas-profesional/{usuario}")
    public ResponseEntity<List<ResponseListaOfertaDTO>> todasLasOfertasProfesional(@PathVariable String usuario){
        return ofertaServicio.todasLasOfertasProfesional(usuario);
    }
    
    
    @GetMapping("/listar-todas-las-ofertas-guest")
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> todasLasOfertasGuest(String usuario){
        return ofertaServicio.todasLasOfertasGuest(usuario);
    }
    
    @GetMapping("listar/{localidad}/{especialidad}")
    public ResponseEntity<List<ResponseOfertaGuestDTO>> filtroBusqueda(@PathVariable String localidad , @PathVariable String especialidad){
        return ofertaServicio.filtroBusqueda(localidad, especialidad);
    }
    
    
    @GetMapping("listarOfertasProfesional/{usuario}")
    public ResponseEntity<List<ResponseOfertaProfesionalDTO>> ofertasProfesionalDisponibles(@PathVariable String usuario){
       return ofertaServicio.ofertasProfesionalDisponibles(usuario);
    }
    
    
}
