package com.egg.salud.servicio;

import com.egg.salud.dto.RegistroProfesionalDTO;
import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.enumeraciones.Rol;
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

@Service
public class ProfesionalServicioImpl implements ProfesionalServicio {

    @Autowired
    private SimpleDateFormat formateo;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

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
        profesional.setEspecialidad(registroDto.getEspecialidad());
        profesional.setMatricula(registroDto.getMatricula());
        profesional.setNacionalidad(registroDto.getNacionalidad());
        profesional.setEstado(true);

        
        profesional.setRol(Rol.PROFESIONAL);

        profesionalRepositorio.save(profesional);

        return new ResponseEntity<>("Usuario registrado exitosamente", HttpStatus.CREATED);
    }

    @Override
    @Transactional
    public ResponseEntity<?> modificarUsuario(String usuario, RequestProfesionalDTO modificarDto) {

        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            if (profesional.getEstado() == true) {
                profesional.setApellido(modificarDto.getApellido());
                profesional.setNombre(modificarDto.getNombre());
                profesional.setDomicilio(modificarDto.getDomicilio());
                profesional.setEspecialidad(modificarDto.getEspecialidad());
                try {
                    profesional.setFecha_nac(formateo.parse(modificarDto.getFecha_nac()));

                } catch (ParseException ex) {
                    Logger.getLogger(ProfesionalServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                profesional.setNacionalidad(modificarDto.getNacionalidad());
                profesional.setMatricula(modificarDto.getMatricula());
                profesional.setDni(modificarDto.getDni());
                profesional.setUsuario(modificarDto.getUsuario());
                profesional.setPassword(new BCryptPasswordEncoder().encode(modificarDto.getPassword()));

                profesionalRepositorio.save(profesional);

                return new ResponseEntity<>("usuario modificado con éxito", HttpStatus.OK);
            }else {
                return new ResponseEntity<>("no se puede modificar un usuario dado de baja", HttpStatus.NOT_ACCEPTABLE);
            }
            }else {
            return new ResponseEntity<>("no se encontró el id de usuario", HttpStatus.NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public ResponseEntity<?> eliminarUsuario(String usuario) {
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);

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

        for (Profesional profesional : listaProfesional) {
            ResponseProfesionalDTO responseProfesional = new ResponseProfesionalDTO();
            responseProfesional.setApellido(profesional.getApellido());
            responseProfesional.setDomicilio(profesional.getDomicilio());
            responseProfesional.setMatricula(profesional.getMatricula());
            responseProfesional.setEspecialidad(profesional.getEspecialidad());
            responseProfesional.setFecha_nac(profesional.getFecha_nac());
            responseProfesional.setNombre(profesional.getNombre());
            responseProfesional.setUsuario(profesional.getUsuario());
            responseProfesional.setDni(profesional.getDni());
            responseProfesional.setUrlFoto(profesional.getUrlFoto());
            responseProfesional.setPassword(profesional.getPassword());
            responseProfesional.setNacionalidad(profesional.getNacionalidad());

            
            listaProfesionalDto.add(responseProfesional);
        }
        return new ResponseEntity<>(listaProfesionalDto, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<?> buscarPorEmail(String usuario){
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);
        if(respuesta.isPresent()){
            Profesional profesional = respuesta.get();
            ResponseProfesionalDTO responseProfesional = new ResponseProfesionalDTO();
            responseProfesional.setApellido(profesional.getApellido());
            responseProfesional.setDomicilio(profesional.getDomicilio());
            responseProfesional.setMatricula(profesional.getMatricula());
            responseProfesional.setEspecialidad(profesional.getEspecialidad());
            responseProfesional.setFecha_nac(profesional.getFecha_nac());
            responseProfesional.setNombre(profesional.getNombre());
            responseProfesional.setUsuario(profesional.getUsuario());
            responseProfesional.setDni(profesional.getDni());
            responseProfesional.setUrlFoto(profesional.getUrlFoto());
            responseProfesional.setPassword(profesional.getPassword());
            responseProfesional.setNacionalidad(profesional.getNacionalidad());

            
            return new ResponseEntity<>(responseProfesional, HttpStatus.ACCEPTED);
            
        }else{
            return new ResponseEntity<>("no se encontro el usuario", HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<List<ResponseProfesionalDTO>> listarEspecialidad(String especialidad) {
        List<Profesional> listaProfesional = profesionalRepositorio.listaPorEspecialidad(especialidad);
        List<ResponseProfesionalDTO> listaProfesionalDto = new ArrayList<>();

        for (Profesional profesional : listaProfesional) {
            ResponseProfesionalDTO responseProfesional = new ResponseProfesionalDTO();
            responseProfesional.setNombre(profesional.getNombre());
            responseProfesional.setApellido(profesional.getApellido());
            responseProfesional.setDni(profesional.getDni());
            responseProfesional.setUrlFoto(profesional.getUrlFoto());
            responseProfesional.setFecha_nac(profesional.getFecha_nac());
            responseProfesional.setEspecialidad(profesional.getEspecialidad());
            responseProfesional.setNacionalidad(profesional.getNacionalidad());
            responseProfesional.setPassword(profesional.getPassword());
            responseProfesional.setUrlFoto(profesional.getUrlFoto());
            responseProfesional.setMatricula(profesional.getMatricula());
            responseProfesional.setDomicilio(profesional.getDomicilio());

            responseProfesional.setUsuario(profesional.getUsuario());

            listaProfesionalDto.add(responseProfesional);
        }
        return new ResponseEntity<>(listaProfesionalDto, HttpStatus.OK);

    }
}
