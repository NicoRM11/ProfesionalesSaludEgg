package com.egg.salud.servicio;

import com.egg.salud.dto.RegistroProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.entidades.Rol;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import com.egg.salud.repositorios.RolRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class ProfesionalServicioImpl implements ProfesionalServicio {

    @Autowired
    private SimpleDateFormat formateo;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Override
    @Transactional
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroProfesionalDTO registroDto) {

        if (profesionalRepositorio.existsByUsuario(registroDto.getUsuario())) {
            return new ResponseEntity<>("el email de usuario ya existe", HttpStatus.NOT_ACCEPTABLE);
        }
        if (profesionalRepositorio.existsByDni(registroDto.getDni())) {
            List<Profesional> profesional = profesionalRepositorio.findByDni(registroDto.getDni()).get();

            for (Profesional aux : profesional) {
                if (aux.getNacionalidad().equals(registroDto.getNacionalidad())) {
                    return new ResponseEntity<>("el dni ya existe", HttpStatus.NOT_ACCEPTABLE);
                }
            }

        }
        Profesional profesional = new Profesional();

        profesional.setUsuario(registroDto.getUsuario());
        profesional.setPassword(new BCryptPasswordEncoder().encode(registroDto.getPassword()));
        profesional.setNombre(registroDto.getNombre());
        profesional.setApellido(registroDto.getApellido());
        profesional.setDni(registroDto.getDni());
        profesional.setDomicilio(registroDto.getDomicilio());
        try {
            profesional.setFecha_nac(formateo.parse(registroDto.getFecha_nac()));

        } catch (ParseException ex) {
            Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        profesional.setEspecialidades(registroDto.getEspecialidades());
        profesional.setMatriculas(registroDto.getMatriculas());
        profesional.setNacionalidad(registroDto.getNacionalidad());
        profesional.setEstado(true);

        Rol roles = rolRepositorio.findByNombre("ROLE_PROFESIONAL").get();
        profesional.setRoles(Collections.singleton(roles));

        profesionalRepositorio.save(profesional);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<?> modificarUsuario(Long idUsuario, RequestProfesionalDTO modificarDto) {

        Optional<Profesional> respuesta = profesionalRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            if (profesional.getEstado() == true) {
                profesional.setApellido(modificarDto.getApellido());
                profesional.setNombre(modificarDto.getNombre());
                profesional.setDomicilio(modificarDto.getDomicilio());
                profesional.setEspecialidades(modificarDto.getEspecialidades());
                try {
                    profesional.setFecha_nac(formateo.parse(modificarDto.getFecha_nac()));

                } catch (ParseException ex) {
                    Logger.getLogger(ProfesionalServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                profesional.setNacionalidad(modificarDto.getNacionalidad());
                profesional.setMatriculas(modificarDto.getMatriculas());
                profesional.setDni(modificarDto.getDni());
                profesional.setUsuario(modificarDto.getUsuario());
                profesional.setPassword(new BCryptPasswordEncoder().encode(modificarDto.getPassword()));

                profesionalRepositorio.save(profesional);
            }
            return new ResponseEntity<>("usuario modificado con éxito", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no se encontró el id de usuario", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<?> eliminarUsuario(Long idUsuario) {
        Optional<Profesional> respuesta = profesionalRepositorio.findById(idUsuario);

        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            profesional.setEstado(false);

            profesionalRepositorio.save(profesional);
            return new ResponseEntity<>("usuario eliminado correctamente", HttpStatus.OK);

        } else {
            return new ResponseEntity<>("no se encontró el id de usuario", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseProfesionalDTO>> listar() {

        List<Profesional> listaProfesional = profesionalRepositorio.findAll();
        List<ResponseProfesionalDTO> listaProfesionalDto = new ArrayList<>();

        for (Profesional aux : listaProfesional) {
            ResponseProfesionalDTO profesionalDTO = new ResponseProfesionalDTO();
            profesionalDTO.setApellido(aux.getApellido());
            profesionalDTO.setNombre(aux.getNombre());
            profesionalDTO.setUsuario(aux.getUsuario());
            profesionalDTO.setFecha_nac(aux.getFecha_nac());
            profesionalDTO.setNacionalidad(aux.getNacionalidad());
            profesionalDTO.setEspecialidades(aux.getEspecialidades());
            profesionalDTO.setMatriculas(aux.getMatriculas());
            profesionalDTO.setDomicilio(aux.getDomicilio());
            profesionalDTO.setEstado(aux.getEstado());
            listaProfesionalDto.add(profesionalDTO);
        }
        return new ResponseEntity<>(listaProfesionalDto, HttpStatus.OK);
    }

}
