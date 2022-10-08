package com.egg.salud.servicio;

import com.egg.salud.dto.RegistroGuestDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Rol;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;

@Service
public class GuestServicioImpl implements GuestServicio {

//    @Autowired
//    private ModelMapper modelMapper;
    @Autowired
    private GuestRepositorio guestRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroGuestDTO registroDto) {


        if(guestRepositorio.existsByUsuario(registroDto.getUsuario())) {
            return new ResponseEntity<>("Ese email de usuario ya existe",HttpStatus.NOT_ACCEPTABLE);
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

        return new ResponseEntity<>("Usuario registrado exitosamente" , HttpStatus.CREATED);
    }

//    private Guest mapearEntidad(RegistroGuestDTO registroDto){
//        Guest guest = modelMapper.map(registroDto, Guest.class);
//        return guest;
//    }
}
