package com.egg.salud.servicio;

import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.enumeraciones.Rol;
import com.egg.salud.exceptions.DataNotFoundException;
import com.egg.salud.exceptions.ResourceNotFoundException;
import com.egg.salud.exceptions.UserIsExistsException;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ProfesionalServicioImpl implements ProfesionalServicio {

    @Autowired
    private SimpleDateFormat formateo;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;

    @Override
    @Transactional
    public String registrarUsuario(@RequestBody RequestProfesionalDTO requestProfesionalDTO) throws Exception {

        if (profesionalRepositorio.existsByUsuario(requestProfesionalDTO.getUsuario())) {
            throw new UserIsExistsException("el email de usuario ya existe");
        }
        if (profesionalRepositorio.existsByDni(requestProfesionalDTO.getDni())) {
            List<Profesional> profesional = profesionalRepositorio.findByDni(requestProfesionalDTO.getDni()).get();

            for (Profesional aux : profesional) {
                if (aux.getNacionalidad().equals(requestProfesionalDTO.getNacionalidad())) {
                    throw new UserIsExistsException("el dni ya existe");
                }
            }

        }
        Profesional profesional = new Profesional();

        profesional.setUsuario(requestProfesionalDTO.getUsuario());
        profesional.setPassword(new BCryptPasswordEncoder().encode(requestProfesionalDTO.getPassword()));
        profesional.setNombre(requestProfesionalDTO.getNombre());
        profesional.setApellido(requestProfesionalDTO.getApellido());
        profesional.setDni(requestProfesionalDTO.getDni());
        profesional.setDomicilio(requestProfesionalDTO.getDomicilio());
        try {
            profesional.setFecha_nac(formateo.parse(requestProfesionalDTO.getFecha_nac()));

        } catch (ParseException ex) {
            Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        profesional.setEspecialidad(requestProfesionalDTO.getEspecialidad());
        profesional.setMatricula(requestProfesionalDTO.getMatricula());
        profesional.setNacionalidad(requestProfesionalDTO.getNacionalidad());
        profesional.setUrlFoto("");
        profesional.setEstado(true);


        profesional.setRol(Rol.PROFESIONAL);

        profesionalRepositorio.save(profesional);

        return ("Usuario registrado exitosamente");
    }

    @Override
    @Transactional
    public String  modificarUsuario(String usuario, RequestProfesionalDTO modificarDto) throws Exception {

        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            if (profesional.getEstado()) {
                profesional.setApellido(modificarDto.getApellido());
                profesional.setNombre(modificarDto.getNombre());
                profesional.setDomicilio(modificarDto.getDomicilio());
                profesional.setEspecialidad(modificarDto.getEspecialidad());
                profesional.setUrlFoto(modificarDto.getUrlFoto());
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

                return "usuario modificado con éxito" ;
            } else {
                throw  new UserIsExistsException("no se puede modificar al usuario ");
            }
        } else {
            throw  new ResourceNotFoundException("no se encontró el email de usuario");
        }

    }

    @Override
    @Transactional
    public String eliminarUsuario(String usuario) throws Exception {
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            profesional.setEstado(false);

            profesionalRepositorio.save(profesional);
            return ("usuario eliminado correctamente");

        } else {
            throw  new ResourceNotFoundException("no se encontró el email de usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseProfesionalDTO> listar() throws Exception{

        List<Profesional> listaProfesional = profesionalRepositorio.findAll();
        List<ResponseProfesionalDTO> listaProfesionalDto = new ArrayList<>();
        if(listaProfesional.size() < 1){
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        for (Profesional profesional : listaProfesional) {
            if(profesional.getEstado()) {
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
        }
        return listaProfesionalDto;
    }

    @Override
    public ResponseProfesionalDTO buscarPorEmail(String usuario) throws Exception {
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);
        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            if (profesional.getEstado()) {

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


                return responseProfesional;

            } else {
                throw new UserIsExistsException("error, usuario dado de baja");
            }
            }else{
                throw new ResourceNotFoundException("no se encontro el usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseProfesionalDTO> listarEspecialidad(String especialidad) throws Exception{
        List<Profesional> listaProfesional = profesionalRepositorio.listaPorEspecialidad(especialidad);
        List<ResponseProfesionalDTO> listaProfesionalDto = new ArrayList<>();

        if(listaProfesional.size() < 1){
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        for (Profesional profesional : listaProfesional) {
            if (profesional.getEstado()) {
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
        }
        return  listaProfesionalDto;

    }
}
