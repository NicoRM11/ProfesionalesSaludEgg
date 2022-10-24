package com.egg.salud.servicio;

import com.egg.salud.dto.RequestGuestDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.enumeraciones.Rol;
import com.egg.salud.exceptions.DataNotFoundException;
import com.egg.salud.exceptions.ResourceNotFoundException;
import com.egg.salud.exceptions.UserIsExistsException;
import com.egg.salud.repositorios.GuestRepositorio;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class GuestServicioImpl implements GuestServicio {

    @Autowired
    private SimpleDateFormat formateo;
    @Autowired
    private GuestRepositorio guestRepositorio;

    @Override
    @Transactional
    public String registrarUsuario(@RequestBody RequestGuestDTO requestDto) throws Exception {

        if (guestRepositorio.existsByUsuario(requestDto.getUsuario())) {
            throw new UserIsExistsException("el email de usuario ya existe");
        }
        if (guestRepositorio.existsByDni(requestDto.getDni())) {
            List<Guest> guest = guestRepositorio.findByDni(requestDto.getDni()).get();

            for (Guest aux : guest) {
                if (aux.getNacionalidad().equals(requestDto.getNacionalidad())) {
                    throw new UserIsExistsException("el dni ya existe");
                }
            }
        }
        Guest guest = new Guest();
        guest.setUsuario(requestDto.getUsuario());
        guest.setApellido(requestDto.getApellido());
        guest.setDni(requestDto.getDni());
        guest.setPassword(new BCryptPasswordEncoder().encode(requestDto.getPassword()));
        guest.setNombre(requestDto.getNombre());
        guest.setObra_social(requestDto.getObra_social());
        guest.setTelefono(requestDto.getTelefono());
        try {
            guest.setFecha_nac(formateo.parse(requestDto.getFecha_nac()));

        } catch (ParseException ex) {
            Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        guest.setNacionalidad(requestDto.getNacionalidad());
        guest.setLocalidad(requestDto.getLocalidad());
        guest.setEstado(true);

        guest.setRol(Rol.GUEST);

        guestRepositorio.save(guest);

        return "usuario registrado exitosamente";
    }

    @Override
    @Transactional
    public String modificarUsuario(String usuario, RequestGuestDTO modificarDto) throws Exception {

        Optional<Guest> respuesta = guestRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Guest guest = respuesta.get();
            if (guest.getEstado() == true) {
                guest.setApellido(modificarDto.getApellido());
                guest.setNombre(modificarDto.getNombre());
                guest.setDni(modificarDto.getDni());
                guest.setUsuario(modificarDto.getUsuario());
                guest.setObra_social(modificarDto.getObra_social());
                guest.setTelefono(modificarDto.getTelefono());
                guest.setUrlFoto(modificarDto.getUrlFoto());
                try {
                    guest.setFecha_nac(formateo.parse(modificarDto.getFecha_nac()));

                } catch (ParseException ex) {
                    Logger.getLogger(GuestServicioImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
                guest.setNacionalidad(modificarDto.getNacionalidad());
                guest.setLocalidad(modificarDto.getLocalidad());
                guest.setPassword(new BCryptPasswordEncoder().encode(modificarDto.getPassword()));

                guestRepositorio.save(guest);
                return "usuario modificado con éxito";
            } else {
                throw new UserIsExistsException("no se puede modificar al usuario");
            }
        } else {
            throw new ResourceNotFoundException("no se encontró el email del usuario");
        }
    }

    @Override
    @Transactional
    public String eliminarUsuario(String usuario) throws Exception {
        Optional<Guest> respuesta = guestRepositorio.findByUsuario(usuario);

        if (respuesta.isPresent()) {
            Guest guest = respuesta.get();
            guest.setEstado(false);

            guestRepositorio.save(guest);
            return "usuario eliminado correctamente";

        } else {
            throw new ResourceNotFoundException("no se encontró el email de usuario");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseGuestDTO> listar() throws Exception {

        List<Guest> listaGuest = guestRepositorio.findAll();
        List<ResponseGuestDTO> listaGuestDto = new ArrayList<>();
        if (listaGuest.size() < 1) {
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        for (Guest guest : listaGuest) {
            ResponseGuestDTO responseGuest = new ResponseGuestDTO();
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
            listaGuestDto.add(responseGuest);
        }
        return listaGuestDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseGuestDTO> listarObraSocial(String obra_social) throws Exception {
        List<Guest> listaGuest = guestRepositorio.listaPorObraSocial(obra_social);
        List<ResponseGuestDTO> listaGuestDto = new ArrayList<>();

        if (listaGuest.size() < 1) {
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        for (Guest guest : listaGuest) {
            if (guest.getEstado()) {
                ResponseGuestDTO responseGuest = new ResponseGuestDTO();
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
                listaGuestDto.add(responseGuest);
            }
        }
        return listaGuestDto;

    }

    @Transactional(readOnly = true)
    @Override
    public ResponseGuestDTO buscarPorEmail(String usuario) throws Exception {

        Optional<Guest> respuesta = guestRepositorio.findByUsuario(usuario);
        if (respuesta.isPresent()) {
            Guest guest = respuesta.get();
            if (guest.getEstado()) {
                ResponseGuestDTO responseGuest = new ResponseGuestDTO();
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
                responseGuest.setTelefono(guest.getTelefono());
                responseGuest.setEstado(guest.getEstado());

                return responseGuest;
            } else {
                throw new UserIsExistsException("error, usuario dado de baja");
            }
        } else {
            throw new ResourceNotFoundException("no se encontro al usuario");
        }

    }

}
