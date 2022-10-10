package com.egg.salud.servicio;

import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Rol;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.RolRepositorio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class GuestServicioImpl implements GuestServicio {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private GuestRepositorio guestRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroGuestDTO registroDto) {

        if(guestRepositorio.existsByUsuario(registroDto.getUsuario()) && guestRepositorio.existsByDni(registroDto.getDni())) {
            return new ResponseEntity<>("el email o DNI de usuario ya existe",HttpStatus.NOT_ACCEPTABLE);
        }
        Guest guest = new Guest();
        guest.setUsuario(registroDto.getUsuario());
        guest.setApellido(registroDto.getApellido());
        guest.setDni(registroDto.getDni());
        guest.setPassword(passwordEncoder.encode(registroDto.getPassword()));
        guest.setNombre(registroDto.getNombre());
        guest.setObra_social(registroDto.getObra_social());
        guest.setTelefono(registroDto.getTelefono());
        guest.setFecha_nac(registroDto.getFecha_nac());
        guest.setNacionalidad(registroDto.getNacionalidad());
        guest.setLocalidad(registroDto.getLocalidad());
        guest.setEstado(true);

        Rol roles = rolRepositorio.findByNombre("ROLE_GUEST").get();
        guest.setRoles(Collections.singleton(roles));

        guestRepositorio.save(guest);

        return new ResponseEntity<>("usuario registrado exitosamente" , HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> modificarUsuario(Long idUsuario, RequestGuestDTO modificarDto) {

        Optional<Guest> respuesta = guestRepositorio.findById(idUsuario);

        if (respuesta.isPresent()){
            Guest guest = respuesta.get();
            if (guest.getEstado() == true){
                guest.setApellido(modificarDto.getApellido());
                guest.setNombre(modificarDto.getNombre());
                guest.setObra_social(modificarDto.getObra_social());
                guest.setTelefono(modificarDto.getTelefono());
                guest.setFecha_nac(modificarDto.getFecha_nac());
                guest.setNacionalidad(modificarDto.getNacionalidad());
                guest.setLocalidad(modificarDto.getLocalidad());

                guestRepositorio.save(guest);
            } else {
                return new ResponseEntity<>("no se puede modificar un usuario inactivo" , HttpStatus.NOT_ACCEPTABLE);
            }
            return new ResponseEntity<>("usuario modificado con éxito" , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no se encontró el id de usuario" , HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> eliminarUsuario(Long idUsuario) {
        Optional<Guest> respuesta = guestRepositorio.findById(idUsuario);

        if (respuesta.isPresent()){
            Guest guest = respuesta.get();
            guest.setEstado(false);

            guestRepositorio.save(guest);
            return new ResponseEntity<>("usuario eliminado correctamente" , HttpStatus.OK);

        } else {
            return new ResponseEntity<>("no se encontró el id de usuario" , HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseGuestDTO>> listar() {

        List<Guest> listaGuest = guestRepositorio.findAll();
        List<ResponseGuestDTO> listaGuestDto = new ArrayList<>();

        for (Guest aux: listaGuest) {
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
