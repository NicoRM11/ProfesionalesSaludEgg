package com.egg.salud.servicio;

import com.egg.salud.dto.CrearOfertaDTO;
import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RequestOfertaProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.entidades.Oferta;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.OfertaRepositorio;
import com.egg.salud.repositorios.ProfesionalRepositorio;
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
public class OfertaServiciolmpl implements OfertaServicio {

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
                oferta.setDisponible(true);
                oferta.setModalidad(crearOfertaDto.getModalidad());
                oferta.setEspecialidad(profesional.getEspecialidad());
                oferta.setUbicacion(profesional.getDomicilio());
                oferta.setLocalidad(crearOfertaDto.getLocalidad());
                oferta.setTelefono(crearOfertaDto.getTelefono());
                oferta.setFecha(crearOfertaDto.getFecha());
                oferta.setHora(crearOfertaDto.getHora());

                ofertaRepositorio.save(oferta);

                return new ResponseEntity<>("Oferta Creada por profesional " + profesional.getNombre(), HttpStatus.CREATED);

            } else {
                return new ResponseEntity<>("Oferta no creada debido a baja de profesional", HttpStatus.NOT_ACCEPTABLE);
            }

        } else {
            return new ResponseEntity<>("Profesional no encontrado", HttpStatus.NOT_FOUND);
        }

    }

//    @Override
//    public ResponseEntity<?> modificarOfertaProfesional(String usuario, RequestOfertaProfesionalDTO modificarDto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ResponseEntity<?> eliminarOfertaProfesional(Long idProfesional) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ResponseEntity<List<ResponseGuestDTO>> listarOfertaProfesional() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ResponseEntity<?> aceptarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    @Override
//    public ResponseEntity<?> cancelarOfertaUsuario(Long idProfesional, RequestGuestDTO modificarDto) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
