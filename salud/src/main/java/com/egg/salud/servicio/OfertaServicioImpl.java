package com.egg.salud.servicio;

import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseListaOfertaDTO;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
                oferta.setConsultorio(crearOfertaDto.getConsultorio());
                oferta.setLocalidad(crearOfertaDto.getLocalidad());
                oferta.setTelefono(crearOfertaDto.getTelefono());
                oferta.setStart(crearOfertaDto.getStart());
                oferta.setEnd(crearOfertaDto.getEnd());
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
                oferta.setStart(requestOfertaProfesionalDTO.getStart());
                oferta.setEnd(requestOfertaProfesionalDTO.getEnd());
                oferta.setConsultorio(requestOfertaProfesionalDTO.getConsultorio());

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

        if (respuesta.isPresent()) {
            List<Oferta> listaOfertaProfesional = ofertaRepositorio.listaPorProfesional(usuario);

            List<ResponseOfertaAceptadaProfesionalDTO> listaOfertaAceptadaProfesionalDTO = new ArrayList<>();

            for (Oferta oferta : listaOfertaProfesional) {

                if (oferta.getDisponible() == false && oferta.getEstado() == true) {
                    ResponseOfertaAceptadaProfesionalDTO ofertaAceptadaDto = new ResponseOfertaAceptadaProfesionalDTO();
                    ofertaAceptadaDto.setGuest(oferta.getGuest());
                    ofertaAceptadaDto.setApellido(oferta.getGuest().getApellido());
                    ofertaAceptadaDto.setFecha_nac(oferta.getGuest().getFecha_nac());
                    ofertaAceptadaDto.setObra_social(oferta.getGuest().getObra_social());
                    ofertaAceptadaDto.setTelefono(oferta.getGuest().getTelefono());

                    ofertaAceptadaDto.setId(oferta.getId());
                    ofertaAceptadaDto.setStart(oferta.getStart());
                    ofertaAceptadaDto.setEnd(oferta.getEnd());
                    ofertaAceptadaDto.setLocalidad(oferta.getLocalidad());
                    ofertaAceptadaDto.setModalidad(oferta.getModalidad());
                    ofertaAceptadaDto.setConsultorio(oferta.getConsultorio());

                    listaOfertaAceptadaProfesionalDTO.add(ofertaAceptadaDto);
                } else {
                    System.out.println("Ninguna oferta aceptada");
                }
            }
            return new ResponseEntity<>(listaOfertaAceptadaProfesionalDTO, HttpStatus.OK);
        }

        Guest asd = new Guest();
        ResponseOfertaAceptadaProfesionalDTO a = new ResponseOfertaAceptadaProfesionalDTO();

        List<ResponseOfertaAceptadaProfesionalDTO> prueba = new ArrayList<>();
        prueba.add(a);
        return new ResponseEntity<>(prueba, HttpStatus.NOT_FOUND);

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseOfertaDisponibleProfesionalDTO>> buscarOfertaProfesionalDisponible() {
        List<Oferta> listaOfertaProfesional = ofertaRepositorio.findAll();
        List<ResponseOfertaDisponibleProfesionalDTO> listaOfertaDisponibleProfesionalDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaProfesional) {

            if (oferta.getDisponible() == true && oferta.getEstado() == true) {
                ResponseOfertaDisponibleProfesionalDTO ofertaDisponibleDto = new ResponseOfertaDisponibleProfesionalDTO();

                ofertaDisponibleDto.setStart(oferta.getStart());
                ofertaDisponibleDto.setEnd(oferta.getEnd());
                ofertaDisponibleDto.setLocalidad(oferta.getLocalidad());
                ofertaDisponibleDto.setModalidad(oferta.getModalidad());
                ofertaDisponibleDto.setConsultorio(oferta.getConsultorio());
                ofertaDisponibleDto.setId(oferta.getId());

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

        if (respuesta.isPresent()) {
            List<Oferta> listaOfertaGuest = ofertaRepositorio.listaPorGuest(usuario);
            List<ResponseOfertaAceptadaGuestDTO> listaOfertaAceptadaGuestDTO = new ArrayList<>();

            for (Oferta oferta : listaOfertaGuest) {

                if (oferta.getDisponible() == false && oferta.getEstado() == true) {
                    ResponseOfertaAceptadaGuestDTO ofertaAceptadaDto = new ResponseOfertaAceptadaGuestDTO();

                    ofertaAceptadaDto.setId(oferta.getId());
                    ofertaAceptadaDto.setNombre(oferta.getProfesional().getNombre());
                    ofertaAceptadaDto.setApellido(oferta.getProfesional().getApellido());
                    ofertaAceptadaDto.setEspecialidad(oferta.getProfesional().getEspecialidad());

                    ofertaAceptadaDto.setStart(oferta.getStart());
                    ofertaAceptadaDto.setEnd(oferta.getEnd());
                    ofertaAceptadaDto.setLocalidad(oferta.getLocalidad());
                    ofertaAceptadaDto.setModalidad(oferta.getModalidad());
                    ofertaAceptadaDto.setConsultorio(oferta.getConsultorio());
                    ofertaAceptadaDto.setTelefono(oferta.getTelefono());

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
        ResponseOfertaAceptadaGuestDTO a = new ResponseOfertaAceptadaGuestDTO();

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

            if (oferta.getDisponible() == true && oferta.getEstado() == true) {
                ResponseOfertaDisponibleGuestDTO ofertaDisponibleDto = new ResponseOfertaDisponibleGuestDTO();

                ofertaDisponibleDto.setId(oferta.getId());
                ofertaDisponibleDto.setStart(oferta.getStart());
                ofertaDisponibleDto.setEnd(oferta.getEnd());
                ofertaDisponibleDto.setLocalidad(oferta.getLocalidad());
                ofertaDisponibleDto.setModalidad(oferta.getModalidad());
                ofertaDisponibleDto.setConsultorio(oferta.getConsultorio());

                ofertaDisponibleDto.setApellido(oferta.getProfesional().getApellido());
                ofertaDisponibleDto.setNombre(oferta.getProfesional().getNombre());
                ofertaDisponibleDto.setTelefono(oferta.getTelefono());
                ofertaDisponibleDto.setEspecialidad(oferta.getEspecialidad());

                listaOfertaDisponibleGuestDTO.add(ofertaDisponibleDto);
            } else {
                System.out.println("Ninguna oferta aceptada");
            }

        }
        return new ResponseEntity<>(listaOfertaDisponibleGuestDTO, HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<ResponseListaOfertaDTO>> todasLasOfertasProfesional(String usuario) {

        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {

            List<Oferta> listaOfertaProfesional = ofertaRepositorio.listaPorProfesional(usuario);

            List<ResponseListaOfertaDTO> listaProfesionalDTO = new ArrayList<>();

            for (Oferta oferta : listaOfertaProfesional) {

                if (oferta.getEstado() == true) {
                    ResponseListaOfertaDTO ofertaAceptadaDto = new ResponseListaOfertaDTO();

                    if (oferta.getDisponible() == false) {
                        ofertaAceptadaDto.setNombre(oferta.getGuest().getNombre());
                        ofertaAceptadaDto.setApellido(oferta.getGuest().getApellido());
                        ofertaAceptadaDto.setTelefonoGuest(oferta.getGuest().getTelefono());
                   } else {
                       // ofertaAceptadaDto.setGuest(new Guest());
                    }
                    
                    ofertaAceptadaDto.setTelefono(oferta.getTelefono());
                    ofertaAceptadaDto.setEspecialidad(oferta.getEspecialidad());
                    ofertaAceptadaDto.setId(oferta.getId());
                    ofertaAceptadaDto.setStart(oferta.getStart());
                    ofertaAceptadaDto.setEnd(oferta.getEnd());
                    ofertaAceptadaDto.setLocalidad(oferta.getLocalidad());
                    ofertaAceptadaDto.setModalidad(oferta.getModalidad());
                    ofertaAceptadaDto.setConsultorio(oferta.getConsultorio());
                    ofertaAceptadaDto.setDisponible(oferta.getDisponible());

                    listaProfesionalDTO.add(ofertaAceptadaDto);
                }
            }
            return new ResponseEntity<>(listaProfesionalDTO, HttpStatus.OK);

        } else {
            System.out.println("holu");
            return null;
        }

//        Guest asd = new Guest();
//        ResponseListaOfertaDTO a = new ResponseListaOfertaDTO
//        List<ResponseOfertaAceptadaProfesionalDTO> prueba = new ArrayList<>();
//        prueba.add(a);
//        return new ResponseEntity<>(prueba, HttpStatus.NOT_FOUND);
//   
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<ResponseOfertaAceptadaGuestDTO>> todasLasOfertasGuest(String usuario) {
        List<Oferta> listaOfertaGuest = ofertaRepositorio.findAll();
        List<ResponseOfertaDisponibleGuestDTO> todasLasOfertasGuestDTO = new ArrayList<>();

        for (Oferta oferta : listaOfertaGuest) {

            if (oferta.getEstado() == true) {
                ResponseOfertaDisponibleGuestDTO ofertaDisponibleDto = new ResponseOfertaDisponibleGuestDTO();

                ofertaDisponibleDto.setId(oferta.getId());
                ofertaDisponibleDto.setStart(oferta.getStart());
                ofertaDisponibleDto.setEnd(oferta.getEnd());
                ofertaDisponibleDto.setLocalidad(oferta.getLocalidad());
                ofertaDisponibleDto.setModalidad(oferta.getModalidad());
                ofertaDisponibleDto.setConsultorio(oferta.getConsultorio());

                ofertaDisponibleDto.setApellido(oferta.getProfesional().getApellido());
                ofertaDisponibleDto.setNombre(oferta.getProfesional().getNombre());
                ofertaDisponibleDto.setTelefono(oferta.getTelefono());
                ofertaDisponibleDto.setEspecialidad(oferta.getEspecialidad());

                todasLasOfertasGuestDTO.add(ofertaDisponibleDto);
            } else {
                System.out.println("Manejo de errores");
            }

        }
        return null;

    }

    @Override
    public ResponseEntity<List<ResponseOfertaDisponibleGuestDTO>> buscarPorLocalidad(String localidad) {

        List<Oferta> listaOfertas = ofertaRepositorio.buscarPorLocalidad(localidad);
        List<ResponseOfertaDisponibleGuestDTO> listaResponse = new ArrayList<>();

        for (Oferta aux : listaOfertas) {
            
            if (aux.getEstado() == true && aux.getDisponible()== true) {
            ResponseOfertaDisponibleGuestDTO response = new ResponseOfertaDisponibleGuestDTO();
            response.setId(aux.getId());
            response.setStart(aux.getStart());
            response.setEnd(aux.getEnd());
            response.setNombre(aux.getProfesional().getNombre());
            response.setApellido(aux.getProfesional().getApellido());
            response.setTelefono(aux.getTelefono());
            response.setLocalidad(aux.getLocalidad());
            response.setConsultorio(aux.getConsultorio());
            response.setModalidad(aux.getModalidad());
            response.setEspecialidad(aux.getEspecialidad());
             
            listaResponse.add(response);
           
        }
        }
         
        return new ResponseEntity<>(listaResponse , HttpStatus.OK);
    
    }
    
}
