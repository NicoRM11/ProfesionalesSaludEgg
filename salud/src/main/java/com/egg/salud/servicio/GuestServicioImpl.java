package com.egg.salud.servicio;

import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.enumeraciones.Rol;
import com.egg.salud.repositorios.GuestRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class GuestServicioImpl implements GuestServicio {

    @Autowired
    private SimpleDateFormat formateo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GuestRepositorio guestRepositorio;
    

    @Override
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroGuestDTO registroDto) {

        if (guestRepositorio.existsByUsuario(registroDto.getUsuario())) {
            return new ResponseEntity<>("el email de usuario ya existe", HttpStatus.NOT_ACCEPTABLE);
        }
        if (guestRepositorio.existsByDni(registroDto.getDni())) {
            List<Guest> guest = guestRepositorio.findByDni(registroDto.getDni()).get();

            for (Guest aux : guest) {
                if (aux.getNacionalidad().equals(registroDto.getNacionalidad())) {
                    return new ResponseEntity<>("el dni ya existe", HttpStatus.NOT_ACCEPTABLE);
                }
            }

        }
        Guest guest = new Guest();
        guest.setUsuario(registroDto.getUsuario());
        guest.setApellido(registroDto.getApellido());
        guest.setDni(registroDto.getDni());
        guest.setPassword(new BCryptPasswordEncoder().encode(registroDto.getPassword()));
        guest.setNombre(registroDto.getNombre());
        guest.setObra_social(registroDto.getObra_social());
        guest.setTelefono(registroDto.getTelefono());
        try {
            guest.setFecha_nac(formateo.parse(registroDto.getFecha_nac()));

        } catch (ParseException ex) {
            Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        guest.setNacionalidad(registroDto.getNacionalidad());
        guest.setLocalidad(registroDto.getLocalidad());
        guest.setEstado(true);

        guest.setRol(Rol.GUEST);

        guestRepositorio.save(guest);

        return new ResponseEntity<>("usuario registrado exitosamente", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> modificarUsuario(String usuario, RequestGuestDTO modificarDto) {

        Optional<Guest> respuesta = guestRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Guest guest = respuesta.get();
            if (guest.getEstado() == true) {
                guest.setApellido(modificarDto.getApellido());
                guest.setNombre(modificarDto.getNombre());
                guest.setDni(modificarDto.getDni());
                guest.setUsuario(modificarDto.getUsuario());
                guest.setObra_social(modificarDto.getObra_social());
                guest.setTelefono(modificarDto.getTelefono());
                try {
                    guest.setFecha_nac(formateo.parse(modificarDto.getFecha_nac()));

                } catch (ParseException ex) {
                    Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                guest.setNacionalidad(modificarDto.getNacionalidad());
                guest.setLocalidad(modificarDto.getLocalidad());
                guest.setPassword(new BCryptPasswordEncoder().encode(modificarDto.getPassword()));

                guestRepositorio.save(guest);
            }
            return new ResponseEntity<>("usuario modificado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no se encontró el id de usuario", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> eliminarUsuario(String usuario) {
        Optional<Guest> respuesta = guestRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Guest guest = respuesta.get();
            guest.setEstado(false);

            guestRepositorio.save(guest);
            return new ResponseEntity<>("usuario eliminado correctamente", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("no se encontró el id de usuario", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseGuestDTO>> listar() {

        List<Guest> listaGuest = guestRepositorio.findAll();
        List<ResponseGuestDTO> listaGuestDto = new ArrayList<>();

        for (Guest aux : listaGuest) {
            ResponseGuestDTO guestDTO = new ResponseGuestDTO();
            guestDTO.setApellido(aux.getApellido());
            guestDTO.setNombre(aux.getNombre());
            guestDTO.setFecha_nac(aux.getFecha_nac());
            guestDTO.setNacionalidad(aux.getNacionalidad());
            guestDTO.setObra_social(aux.getObra_social());
            guestDTO.setTelefono(aux.getTelefono());
            guestDTO.setLocalidad(aux.getLocalidad());
            guestDTO.setEstado(aux.getEstado());
            listaGuestDto.add(guestDTO);
        }
        return new ResponseEntity<>(listaGuestDto, HttpStatus.OK);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseGuestDTO>> listarObraSocial(String obra_social) {
        List<Guest> listaGuest = guestRepositorio.listaPorObraSocial(obra_social);
        List<ResponseGuestDTO> listaGuestDto = new ArrayList<>();

        for (Guest aux : listaGuest) {
            ResponseGuestDTO guestDTO = new ResponseGuestDTO();
            guestDTO.setApellido(aux.getApellido());
            guestDTO.setNombre(aux.getNombre());
            guestDTO.setFecha_nac(aux.getFecha_nac());
            guestDTO.setNacionalidad(aux.getNacionalidad());
            guestDTO.setObra_social(aux.getObra_social());
            guestDTO.setTelefono(aux.getTelefono());
            guestDTO.setLocalidad(aux.getLocalidad());
            guestDTO.setEstado(aux.getEstado());
            listaGuestDto.add(guestDTO);
        }
        return new ResponseEntity<>(listaGuestDto, HttpStatus.OK);

    }

}
