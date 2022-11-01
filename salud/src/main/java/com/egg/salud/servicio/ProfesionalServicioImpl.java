package com.egg.salud.servicio;

import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.exceptions.DataNotFoundException;
import com.egg.salud.exceptions.ResourceNotFoundException;
import com.egg.salud.exceptions.UserIsExistsException;
import com.egg.salud.mapper.MapperProfesional;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    @Autowired
    private MapperProfesional mapper;

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

        Profesional profesional = mapper.map(requestProfesionalDTO);

        profesionalRepositorio.save(profesional);

        return ("Usuario registrado exitosamente");
    }

    @Override
    @Transactional
    public String modificarUsuario(String usuario, RequestProfesionalDTO modificarDto) throws Exception {

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

                return "usuario modificado con éxito";
            } else {
                throw new UserIsExistsException("no se puede modificar al usuario ");
            }
        } else {
            throw new ResourceNotFoundException("no se encontró el email de usuario");
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
            throw new ResourceNotFoundException("no se encontró el email de usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseProfesionalDTO> listar() throws Exception {

        List<Profesional> listaProfesional = profesionalRepositorio.findAll();

        if (listaProfesional.size() < 1) {
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        List<ResponseProfesionalDTO> listaProfesionalDto = mapper.map(listaProfesional);

        return listaProfesionalDto;
    }

    @Override
    public ResponseProfesionalDTO buscarPorEmail(String usuario) throws Exception {
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuario);
        if (respuesta.isPresent()) {
            Profesional profesional = respuesta.get();
            if (profesional.getEstado()) {

                ResponseProfesionalDTO responseProfesional = mapper.map(profesional);
                return responseProfesional;

            } else {
                throw new UserIsExistsException("error, usuario dado de baja");
            }
        } else {
            throw new ResourceNotFoundException("no se encontro el usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseProfesionalDTO> listarEspecialidad(String especialidad) throws Exception {
        List<Profesional> listaProfesional = profesionalRepositorio.listaPorEspecialidad(especialidad);

        if (listaProfesional.size() < 1) {
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        List<ResponseProfesionalDTO> listaProfesionalDto = mapper.map(listaProfesional);

        return listaProfesionalDto;

    }
}
