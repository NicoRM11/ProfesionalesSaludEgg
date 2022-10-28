package com.egg.salud.servicio;

import com.egg.salud.dto.RequestFicheroDTO;
import com.egg.salud.dto.ResponseFicheroDTO;
import java.util.List;

public interface FicheroGuestServicio {
    
    public String crearFichero(RequestFicheroDTO request , String usuarioaGuest, String usuarioProfesional) throws Exception;
    
   public String eliminarFichero(String usuario , Long id) throws Exception;
    
    public List<ResponseFicheroDTO> listaFicheroGuest(String usuario) throws Exception;
    
    public List<ResponseFicheroDTO> listaFicheroProfesional(String usuario , String especialidad) throws Exception;
    
    
}
