package com.egg.salud.servicio;

import com.egg.salud.dto.RequestFicheroDTO;
import com.egg.salud.dto.ResponseFicheroDTO;
import com.egg.salud.entidades.FicheroGuest;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.exceptions.ResourceNotFoundException;
import com.egg.salud.exceptions.UserIsExistsException;
import com.egg.salud.repositorios.FicheroGuestRepositorio;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FicheroGuestServicioImpl implements FicheroGuestServicio {
    
    @Autowired
    private FicheroGuestRepositorio ficheroRepositorio;
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;
    @Autowired
    private GuestRepositorio guestRepositorio;
    
    
    @Override
    public String crearFichero(RequestFicheroDTO request, String usuarioProfesional, String usuarioGuest) throws Exception{
        
        Optional<Guest> busqueda = guestRepositorio.findByUsuario(usuarioGuest);
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuarioProfesional);
        
        if (busqueda.isPresent() && respuesta.isPresent()) {
            
            Guest guest = busqueda.get();
            
            if (guest.getEstado()) {
                
                FicheroGuest fichero = new FicheroGuest();
                fichero.setGuest(guest);
                fichero.setDescripcion(request.getDescripcion());
                fichero.setFechaConsulta(request.getFechaConsulta());
                
                ficheroRepositorio.save(fichero);
                return "Fichero creado correctamente";
            } else {
              throw new UserIsExistsException("usuario dado de baja");
            }
        } else {
            throw new ResourceNotFoundException("usuario no encontrado");
        }
    }
    
    @Override
    public String eliminarFichero(String usuario, Long id)  throws Exception{
        
        Optional<Profesional> busqueda = profesionalRepositorio.findByUsuario(usuario);
        Optional<FicheroGuest> respuesta = ficheroRepositorio.findById(id);
                      
        
        if (busqueda.isPresent() && respuesta.isPresent()) {
             
            Profesional profesional = busqueda.get();
            FicheroGuest fichero = respuesta.get();
            
            if( fichero.getId().equals(profesional.getFicheroGuest())){
            
            if (fichero.getEstado()) {
                
                fichero.setEstado(false);
               
                
                ficheroRepositorio.save(fichero);
                return "Fichero eliminado correctamente";
            } }else {
              throw new UserIsExistsException("usuario dado de baja");
            }
        } else {
            throw new ResourceNotFoundException("usuario no encontrado");
        }
        return null;
    }
    
    @Override
    public List<ResponseFicheroDTO> listaFicheroGuest(String usuario)  throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<ResponseFicheroDTO> listaFicheroProfesional(String usuario, String especialidad)  throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
