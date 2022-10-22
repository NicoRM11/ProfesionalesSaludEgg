package com.egg.salud.servicio;

import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Oferta;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.OfertaRepositorio;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Walter
 */
@Service
public class OfertaServicioImpl implements OfertaServicio {
    
     @Autowired
    private SimpleDateFormat formateo;

    @Autowired
    private GuestRepositorio guestRepositorio;

    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @Autowired
    private OfertaRepositorio ofertaRepositorio;

    @Override
    @Transactional
    public ResponseEntity<?> crearOfertaProfesional(String usuario, @RequestBody CrearOfertaDTO crearOfertaDto) {
        
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            if (profesional.getEstado() == true) {
                Oferta oferta = new Oferta();
                oferta.setProfesional(profesional);
                oferta.setDisponible(true);
                oferta.setModalidad(crearOfertaDto.getModalidad());
                oferta.setEspecialidad(profesional.getEspecialidad());
                oferta.setUbicacion(profesional.getDomicilio());
                oferta.setLocalidad(crearOfertaDto.getLocalidad());
                oferta.setTelefono(crearOfertaDto.getTelefono());
                oferta.setFecha(crearOfertaDto.getFecha());
                oferta.setHora(crearOfertaDto.getHora());
                oferta.setEstado(true);

                ofertaRepositorio.save(oferta);

                return new ResponseEntity<>("Oferta Creada por profesional " + profesional.getNombre(), HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("Oferta no creada debido a baja de profesional", HttpStatus.NOT_ACCEPTABLE);
            }

        } else {
            return new ResponseEntity<>("Profesional no encontrado", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> modificarOferta(Long id, RequestOfertaProfesionalDTO requestOfertaProfesionalDTO) {
        
        Optional<Oferta> respuesta = ofertaRepositorio.findById(id);
        
        if (respuesta.isPresent()) {
            Oferta oferta = respuesta.get();
            
            if (oferta.getProfesional().getEstado() == true && oferta.getEstado() == true) {
                
                              
                
                oferta.setModalidad(requestOfertaProfesionalDTO.getModalidad());
                oferta.setLocalidad(requestOfertaProfesionalDTO.getLocalidad());
                oferta.setTelefono(requestOfertaProfesionalDTO.getTelefono());
                oferta.setFecha(requestOfertaProfesionalDTO.getFecha());
                oferta.setHora(requestOfertaProfesionalDTO.getHora());
                
                

                ofertaRepositorio.save(oferta);
                return new ResponseEntity<>("usuario modificado con éxito", HttpStatus.OK);
            } else{
                return new ResponseEntity<>("no se puede modificar un usuario dado de baja", HttpStatus.NOT_ACCEPTABLE);
             }
        } else {
            return new ResponseEntity<>("no se encontró el id de la oferta", HttpStatus.NOT_FOUND);
        }
    }
    
    
    @Override
    @Transactional
    public ResponseEntity<?> eliminarOfertaProfesional(Long id) {
        
        Optional<Oferta> respuesta = ofertaRepositorio.findById(id);

        if (respuesta.isPresent()) {
            
            Oferta oferta = respuesta.get();
            if (oferta.getProfesional().getEstado() == true){
               oferta.setEstado(false);

            ofertaRepositorio.save(oferta);
            return new ResponseEntity<>("oferta eliminada correctamente", HttpStatus.OK);
 
            
            
        } else{
                return new ResponseEntity<>("no se puede modificar porque el profesional esta dado de baja", HttpStatus.NOT_ACCEPTABLE);
             }
        } else {
            return new ResponseEntity<>("no se encontró el id de la oferta", HttpStatus.NOT_FOUND);
        }
        
             
        
    }
    

    @Override
    @Transactional(readOnly=true)
    public ResponseEntity<List<ResponseOfertaAceptadaProfesionalDTO>> buscarOfertaProfesionalAceptadas() {
        
        List<Oferta> listaOfertaAceptada = ofertaRepositorio.findAll();
        List<ResponseOfertaAceptadaProfesionalDTO> listaOfertaAceptadaProfesionalDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaAceptada) {
            
            if (oferta.getGuest().getId()==1){
                
            
            ResponseOfertaAceptadaProfesionalDTO ofertaAceptadaDto = new ResponseOfertaAceptadaProfesionalDTO();
            
            ofertaAceptadaDto.setNombre_guest(oferta.getGuest().getNombre());
            ofertaAceptadaDto.setApellido_guest(oferta.getGuest().getApellido());
            ofertaAceptadaDto.setFecha_nac_guest(oferta.getGuest().getFecha_nac());
            ofertaAceptadaDto.setObra_social_guest(oferta.getGuest().getObra_social());
            ofertaAceptadaDto.setTelefono_guest(oferta.getGuest().getTelefono());
            
            ofertaAceptadaDto.setFecha_turno(oferta.getFecha());
            ofertaAceptadaDto.setHora_turno(oferta.getHora());
            ofertaAceptadaDto.setLocalidad_consultorio(oferta.getLocalidad());
            ofertaAceptadaDto.setModalidad(oferta.getModalidad());
            ofertaAceptadaDto.setUbicacion_consultorio(oferta.getUbicacion());
            
            listaOfertaAceptadaProfesionalDTO.add(ofertaAceptadaDto);
        } else{
                System.out.println("Error");
            }}
        return new ResponseEntity<>(listaOfertaAceptadaProfesionalDTO, HttpStatus.OK);
        }
    
    }

    
        
    

    


