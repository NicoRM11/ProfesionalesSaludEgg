package com.egg.salud.servicio;

import com.egg.salud.dto.LoginDTO;
import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.dto.ResponseUsuarioDTO;

import java.util.List;
import org.springframework.security.core.userdetails.User;

public interface UsuarioServicio {

    public String registrarAdmin(RequestUsuarioDTO request) throws Exception;

    public String modificarAdmin(String request, String usuario) throws Exception;

    public List<ResponseUsuarioDTO> listarAdmin();
    
    public List<ResponseGuestDTO> listarGuest();
    
    public List<ResponseProfesionalDTO> listarProfesional() throws Exception;;

    public String eliminarAdmin(String usuario) throws Exception;
    
    public User login(LoginDTO login) throws Exception;
    
    public ResponseUsuarioDTO buscarUsuario(String usuario) throws Exception;
    
    public String altaUsuario(String usuario) throws Exception;
    
    public List<ResponseUsuarioDTO> listaUsuariosCompleta() throws Exception;

}
