package com.egg.salud.servicio;


import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;

import java.util.List;

public interface GuestServicio {

    public String registrarUsuario(RequestGuestDTO requestDto) throws Exception;

    public String modificarUsuario(String usuario, RequestGuestDTO modificarDto) throws Exception;

    public String eliminarUsuario(String usuario) throws Exception;

    public List<ResponseGuestDTO> listar() throws Exception;

    public List<ResponseGuestDTO> listarObraSocial(String obra_social) throws Exception; 
    
    public ResponseGuestDTO buscarPorEmail(String usuario) throws Exception;
    
}
