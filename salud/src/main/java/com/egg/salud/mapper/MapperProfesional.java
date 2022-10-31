package com.egg.salud.mapper;

import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.enumeraciones.Rol;
import com.egg.salud.servicio.GuestServicioImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class MapperProfesional {

    @Autowired
    private SimpleDateFormat formateo;

    public Profesional map(RequestProfesionalDTO requestProfesionalDTO) {

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
        profesional.setEstado(true);
        profesional.setRol(Rol.PROFESIONAL);

        return profesional;

    }

    public ResponseProfesionalDTO map (Profesional profesional){

        ResponseProfesionalDTO response = new ResponseProfesionalDTO();
        response.setEstado(profesional.getEstado());
        response.setId(profesional.getId());
        response.setApellido(profesional.getApellido());
        response.setDomicilio(profesional.getDomicilio());
        response.setMatricula(profesional.getMatricula());
        response.setEspecialidad(profesional.getEspecialidad());
        response.setFecha_nac(profesional.getFecha_nac());
        response.setNombre(profesional.getNombre());
        response.setUsuario(profesional.getUsuario());
        response.setDni(profesional.getDni());
        response.setUrlFoto(profesional.getUrlFoto());
        response.setPassword(profesional.getPassword());
        response.setNacionalidad(profesional.getNacionalidad());

        return response;

    }

    public List<ResponseProfesionalDTO> map(List<Profesional> listaProfesional){

        List<ResponseProfesionalDTO> listaProfesionalDto = new ArrayList<>();

        for (Profesional profesional : listaProfesional) {
            listaProfesionalDto.add(map(profesional));
        }

        return listaProfesionalDto;
    }


    }

