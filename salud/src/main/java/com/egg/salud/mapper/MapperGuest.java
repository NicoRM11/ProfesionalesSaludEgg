package com.egg.salud.mapper;

import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.enumeraciones.Rol;
import com.egg.salud.servicio.GuestServicioImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MapperGuest {

    @Autowired
    private SimpleDateFormat formateo;

    public Guest map(RequestGuestDTO request) {

        Guest guest = new Guest();
        guest.setUsuario(request.getUsuario());
        guest.setApellido(request.getApellido());
        guest.setDni(request.getDni());
        guest.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        guest.setNombre(request.getNombre());
        guest.setObra_social(request.getObra_social());
        guest.setTelefono(request.getTelefono());
        try {
            guest.setFecha_nac(formateo.parse(request.getFecha_nac()));

        } catch (ParseException ex) {
            Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        guest.setNacionalidad(request.getNacionalidad());
        guest.setLocalidad(request.getLocalidad());
        guest.setEstado(true);

        guest.setRol(Rol.GUEST);

        return guest;
    }
    
    

    public ResponseGuestDTO map(Guest guest) {

        ResponseGuestDTO responseGuest = new ResponseGuestDTO();
        responseGuest.setId(guest.getId());
        responseGuest.setNombre(guest.getNombre());
        responseGuest.setApellido(guest.getApellido());
        responseGuest.setDni(guest.getDni());
        responseGuest.setUrlFoto(guest.getUrlFoto());
        responseGuest.setFecha_nac(guest.getFecha_nac());
        responseGuest.setLocalidad(guest.getLocalidad());
        responseGuest.setNacionalidad(guest.getNacionalidad());
        responseGuest.setPassword(guest.getPassword());
        responseGuest.setObra_social(guest.getObra_social());
        responseGuest.setUsuario(guest.getUsuario());
        responseGuest.setEstado(guest.getEstado());
        responseGuest.setTelefono(guest.getTelefono());
        
        return responseGuest;

    }
    
    
    public List<ResponseGuestDTO> map(List<Guest> listaGuest){
         
        List<ResponseGuestDTO> listaGuestDto = new ArrayList<>();
        
        for (Guest guest : listaGuest) {
            listaGuestDto.add(map(guest));
        }
        
        return listaGuestDto;
        
    }
    
    

}
