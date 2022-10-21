package com.egg.salud.servicio;

import com.egg.salud.dto.RequestProfesionalDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;

import java.util.List;

public interface ProfesionalServicio {


    public String registrarUsuario(RequestProfesionalDTO requestProfesionalDTO) throws Exception;

    public String modificarUsuario(String usuario, RequestProfesionalDTO modificarDto) throws Exception;

    public String eliminarUsuario(String usuario) throws Exception;

    public List<ResponseProfesionalDTO> listar() throws Exception;
    
    public ResponseProfesionalDTO buscarPorEmail(String usuario) throws Exception;

    public List<ResponseProfesionalDTO> listarEspecialidad(String especialidad) throws Exception;

}
