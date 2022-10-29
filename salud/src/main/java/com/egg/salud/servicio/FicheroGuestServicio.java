package com.egg.salud.servicio;

import com.egg.salud.dto.RequestFicheroDTO;
import com.egg.salud.dto.ResponseFicheroGuestDTO;
import com.egg.salud.dto.ResponseFicheroProfesionalDTO;
import java.util.List;

public interface FicheroGuestServicio {
    
    public String crearFichero(RequestFicheroDTO request , String usuarioGuest, String usuarioProfesional) throws Exception;
    
   public String eliminarFichero(String usuario , Long id) throws Exception;
    
    public List<ResponseFicheroProfesionalDTO> listaFicheroGuest(String usuario, String especialidad) throws Exception;

    public List<ResponseFicheroGuestDTO> listarFicheroGuestForProfesional(String usuario, String especialidad) throws Exception;

    
}
