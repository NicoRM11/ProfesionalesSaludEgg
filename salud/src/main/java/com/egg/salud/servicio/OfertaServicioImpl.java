package com.egg.salud.servicio;

import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaGuestDTO;
import com.egg.salud.dto.ResponseOfertaAceptadaProfesionalDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleGuestDTO;
import com.egg.salud.dto.ResponseOfertaDisponibleProfesionalDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Oferta;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.OfertaRepositorio;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                oferta.setUbicacion(crearOfertaDto.getDomicilio_consultorio());
                oferta.setLocalidad(crearOfertaDto.getLocalidad_consultorio());
                oferta.setTelefono(crearOfertaDto.getTelefono_consultorio());
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
    public ResponseEntity<?> modificarOferta(Long id, RequestOfertaProfesionalDTO requestOfertaProfesionalDTO, String usuario) {

        Optional<Oferta> respuesta = ofertaRepositorio.findById(id);
        Optional<Profesional> respuesta2 = profesionalRepositorio.findByUsuario(usuario);
        if (respuesta.isPresent() && respuesta2.isPresent()) {
            Oferta oferta = respuesta.get();

            if (oferta.getProfesional().getEstado() == true && oferta.getEstado() == true) {

                oferta.setModalidad(requestOfertaProfesionalDTO.getModalidad());
                oferta.setLocalidad(requestOfertaProfesionalDTO.getLocalidad());
                oferta.setTelefono(requestOfertaProfesionalDTO.getTelefono());
                oferta.setFecha(requestOfertaProfesionalDTO.getFecha());
                oferta.setHora(requestOfertaProfesionalDTO.getHora());

                ofertaRepositorio.save(oferta);
                return new ResponseEntity<>("usuario modificado con éxito", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("no se puede modificar un usuario dado de baja", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>("no se encontró el id de la oferta", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> eliminarOfertaProfesional(Long id, String usuario) {

        Optional<Oferta> respuesta = ofertaRepositorio.findById(id);
        Optional<Profesional> respuesta2 = profesionalRepositorio.findByUsuario(usuario);
        
        if (respuesta.isPresent() && respuesta2.isPresent()) {

            Oferta oferta = respuesta.get();
            if (oferta.getProfesional().getEstado() == true) {
                oferta.setEstado(false);

                ofertaRepositorio.save(oferta);
                return new ResponseEntity<>("oferta eliminada correctamente", HttpStatus.OK);

            } else {
                return new ResponseEntity<>("no se puede modificar porque el profesional esta dado de baja", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>("no se encontró el id de la oferta", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseOfertaAceptadaProfesionalDTO>> buscarOfertaProfesionalAceptadas(String usuario) {
        
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);
        
        if(respuesta.isPresent()){
//            Profesional profesional = respuesta.get();
            
        
        //Intentar buscar por id distinto de null
        List<Oferta> listaOfertaProfesional = ofertaRepositorio.listaPorProfesional(usuario);
        
        List<ResponseOfertaAceptadaProfesionalDTO> listaOfertaAceptadaProfesionalDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaProfesional) {

            if (oferta.getDisponible() == false) {
                ResponseOfertaAceptadaProfesionalDTO ofertaAceptadaDto = new ResponseOfertaAceptadaProfesionalDTO();
                ofertaAceptadaDto.setGuest(oferta.getGuest());
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
            } else {
                System.out.println("Ninguna oferta aceptada");
            }

        }
        
        return new ResponseEntity<>(listaOfertaAceptadaProfesionalDTO, HttpStatus.OK);
        } 

        Guest asd = new Guest();
        //
        //Manejo de errores
        //
        ResponseOfertaAceptadaProfesionalDTO a = new ResponseOfertaAceptadaProfesionalDTO(asd,"asd","asd","asd",
                1L,new Date(),new Date(),new Date(),"asd","asd","asd");
        
        List<ResponseOfertaAceptadaProfesionalDTO> prueba = new ArrayList<>();
        prueba.add(a);
        return new ResponseEntity<>(prueba, HttpStatus.NOT_FOUND);
        //
        //
        //
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseOfertaDisponibleProfesionalDTO>> buscarOfertaProfesionalDisponible() {
        List<Oferta> listaOfertaProfesional = ofertaRepositorio.findAll();
        List<ResponseOfertaDisponibleProfesionalDTO> listaOfertaDisponibleProfesionalDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaProfesional) {

            if (oferta.getDisponible() == true) {
                ResponseOfertaDisponibleProfesionalDTO ofertaDisponibleDto = new ResponseOfertaDisponibleProfesionalDTO();
                                
                ofertaDisponibleDto.setFecha_turno(oferta.getFecha());
                ofertaDisponibleDto.setHora_turno(oferta.getHora());
                ofertaDisponibleDto.setLocalidad_consultorio(oferta.getLocalidad());
                ofertaDisponibleDto.setModalidad(oferta.getModalidad());
                ofertaDisponibleDto.setUbicacion_consultorio(oferta.getUbicacion());

                listaOfertaDisponibleProfesionalDTO.add(ofertaDisponibleDto);
            } else {
                System.out.println("Ninguna oferta aceptada");
            }

        }
        return new ResponseEntity<>(listaOfertaDisponibleProfesionalDTO, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> aceptarOfertaGuest(String usuario, Long id) {
        
         Optional<Oferta> respuesta_oferta = ofertaRepositorio.findById(id);
         Optional<Guest> respuesta_guest = guestRepositorio.findByUsuario(usuario);

        if (respuesta_oferta.isPresent() && respuesta_guest.isPresent()) {
            Oferta oferta = respuesta_oferta.get();
            Guest guest = respuesta_guest.get();

            if (oferta.getProfesional().getEstado() == true && oferta.getEstado() == true 
                    && guest.getEstado() == true) {

                oferta.setDisponible(false);
                oferta.setGuest(guest);

                ofertaRepositorio.save(oferta);
                return new ResponseEntity<>("el usuario ha aceptado la oferta con éxito", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("no se puede aceptar la oferta porque alguien no esta dado de alta", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>("no se encontró el id de la oferta o del usuario", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> cancelarOfertaGuest(String usuario, Long id) {
        
        Optional<Oferta> respuesta_oferta = ofertaRepositorio.findById(id);
         Optional<Guest> respuesta_guest = guestRepositorio.findByUsuario(usuario);

        if (respuesta_oferta.isPresent() && respuesta_guest.isPresent()) {
            Oferta oferta = respuesta_oferta.get();
            Guest guest = respuesta_guest.get();

            if (oferta.getProfesional().getEstado() == true && oferta.getEstado() == true 
                    && guest.getEstado() == true) {

                oferta.setDisponible(true);
                oferta.setGuest(null);

                ofertaRepositorio.save(oferta);
                return new ResponseEntity<>("el usuario ha cancelado la oferta con éxito", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("no se puede cancelar la oferta porque alguien no esta dado de alta", HttpStatus.NOT_ACCEPTABLE);
            }
        } else {
            return new ResponseEntity<>("no se encontró el id de la oferta o del usuario", HttpStatus.NOT_FOUND);
        }

        

    }

    @Override
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> buscarOfertaGuestAceptadas(String usuario) {
        
        Optional<Guest> respuesta = guestRepositorio.findByUsuario(usuario);
        
        if (respuesta.isPresent()){
            List<Oferta> listaOfertaGuest = ofertaRepositorio.listaPorGuest(usuario);
            List<ResponseOfertaAceptadaGuestDTO> listaOfertaAceptadaGuestDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaGuest) {

            if (oferta.getDisponible() == false) {
                ResponseOfertaAceptadaGuestDTO ofertaAceptadaDto = new ResponseOfertaAceptadaGuestDTO();
                
                ofertaAceptadaDto.setNombre_profesional(oferta.getProfesional().getNombre());
                ofertaAceptadaDto.setApellido_profesional(oferta.getProfesional().getApellido());
                ofertaAceptadaDto.setEspecialidad(oferta.getProfesional().getEspecialidad());

                ofertaAceptadaDto.setFecha_turno(oferta.getFecha());
                ofertaAceptadaDto.setHora_turno(oferta.getHora());
                ofertaAceptadaDto.setLocalidad_consultorio(oferta.getLocalidad());
                ofertaAceptadaDto.setModalidad(oferta.getModalidad());
                ofertaAceptadaDto.setUbicacion_consultorio(oferta.getUbicacion());
                ofertaAceptadaDto.setTelefono_consultorio(oferta.getTelefono());

                listaOfertaAceptadaGuestDTO.add(ofertaAceptadaDto);
            } else {
                System.out.println("Ninguna oferta aceptada");
            }

        }
        return new ResponseEntity<>(listaOfertaAceptadaGuestDTO, HttpStatus.OK);
        } 
        
        //
        //Manejo de errores
        //
        ResponseOfertaAceptadaGuestDTO a = new ResponseOfertaAceptadaGuestDTO("asd","asd",1L,"es",new Date(),new Date(),"as","as","as");
        
        List<ResponseOfertaAceptadaGuestDTO> prueba = new ArrayList<>();
        prueba.add(a);
        return new ResponseEntity<>(prueba, HttpStatus.NOT_FOUND);
        //
        //
        //
    }

    @Override
    public ResponseEntity<List<ResponseOfertaDisponibleGuestDTO>> buscarOfertaGuestDisponible() {
        
        List<Oferta> listaOfertaGuest = ofertaRepositorio.findAll();
        List<ResponseOfertaDisponibleGuestDTO> listaOfertaDisponibleGuestDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaGuest) {

            if (oferta.getDisponible() == true) {
                ResponseOfertaDisponibleGuestDTO ofertaDisponibleDto = new ResponseOfertaDisponibleGuestDTO();
                                
                ofertaDisponibleDto.setFecha_turno(oferta.getFecha());
                ofertaDisponibleDto.setHora_turno(oferta.getHora());
                ofertaDisponibleDto.setLocalidad_consultorio(oferta.getLocalidad());
                ofertaDisponibleDto.setModalidad(oferta.getModalidad());
                ofertaDisponibleDto.setUbicacion_consultorio(oferta.getUbicacion());
                
                ofertaDisponibleDto.setApellido_profesional(oferta.getProfesional().getApellido());
                ofertaDisponibleDto.setNombre_profesional(oferta.getProfesional().getNombre());
                ofertaDisponibleDto.setTelefono_consultorio(oferta.getTelefono());
                ofertaDisponibleDto.setEspecialidad(oferta.getEspecialidad());

                listaOfertaDisponibleGuestDTO.add(ofertaDisponibleDto);
            } else {
                System.out.println("Ninguna oferta aceptada");
            }

        }
        return new ResponseEntity<>(listaOfertaDisponibleGuestDTO, HttpStatus.OK);
    }
        
    
    
    
    
    
    
    
    
    }
    
    




