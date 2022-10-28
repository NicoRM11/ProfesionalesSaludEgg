package com.egg.salud.servicio;

import com.egg.salud.dto.RequestFicheroDTO;
import com.egg.salud.dto.ResponseFicheroGuestDTO;
import com.egg.salud.dto.ResponseFicheroProfesionalDTO;
import com.egg.salud.entidades.FicheroGuest;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.exceptions.DataNotFoundException;
import com.egg.salud.exceptions.ResourceNotFoundException;
import com.egg.salud.exceptions.UserIsExistsException;
import com.egg.salud.repositorios.FicheroGuestRepositorio;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import java.util.ArrayList;
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
    public String crearFichero(RequestFicheroDTO request, String usuarioGuest, String usuarioProfesional) throws Exception {

        Optional<Guest> busqueda = guestRepositorio.findByUsuario(usuarioGuest);
        Optional<Profesional> respuesta = profesionalRepositorio.findByUsuario(usuarioProfesional);

        if (busqueda.isPresent() && respuesta.isPresent()) {

            Guest guest = busqueda.get();
            Profesional profesional = respuesta.get();

            if (guest.getEstado()) {

                FicheroGuest fichero = new FicheroGuest();
                fichero.setGuest(guest);
                fichero.setDescripcion(request.getDescripcion());
                fichero.setFechaConsulta(request.getFechaConsulta());
                fichero.setEstado(true);
                fichero.setProfesional(profesional);

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
    public String eliminarFichero(String usuario, Long id) throws Exception {

        Optional<Profesional> busqueda = profesionalRepositorio.findByUsuario(usuario);
        Optional<FicheroGuest> respuesta = ficheroRepositorio.findById(id);

        if (busqueda.isPresent() && respuesta.isPresent()) {

            Profesional profesional = busqueda.get();
            FicheroGuest fichero = respuesta.get();

            if (fichero.getProfesional().getId().equals(profesional.getId())) {

                if (fichero.getEstado()) {

                    fichero.setEstado(false);

                    ficheroRepositorio.save(fichero);
                    return "Fichero eliminado correctamente";
                }
            } else {
                throw new UserIsExistsException("la ficha medica del paciente no le corresponde a dicho profesional ");
            }
        } else {
            throw new ResourceNotFoundException("usuario no encontrado");
        }
        return null;
    }

    @Override
    public List<ResponseFicheroProfesionalDTO> listaFicheroGuest(String usuario) throws Exception {

        Optional<Guest> busqueda = guestRepositorio.findByUsuario(usuario);

        if (busqueda.isPresent()) {
            Guest guest = busqueda.get();
            if (guest.getEstado()) {
                List<FicheroGuest> listaFichero = ficheroRepositorio.listaFicheroGuest(usuario);

                List<ResponseFicheroProfesionalDTO> listaResponse = new ArrayList<>();

                for (FicheroGuest aux : listaFichero) {
                    ResponseFicheroProfesionalDTO response = new ResponseFicheroProfesionalDTO();
                    response.setDescripcion(aux.getDescripcion());
                    response.setFechaConsulta(aux.getFechaConsulta());
                    response.setNombreProfesional(aux.getProfesional().getNombre());
                    response.setApellidoProfesional(aux.getProfesional().getApellido());
                    response.setEspecialidad(aux.getProfesional().getEspecialidad());

                    listaResponse.add(response);
                }
                if (listaFichero.size() < 1) {
                    throw new DataNotFoundException("No se encuentran registros");
                } else {
                    return listaResponse;
                }

            } else {
                throw new UserIsExistsException("Usuario dado de baja");
            }
        } else {
            throw new ResourceNotFoundException("No se encontró al usuario");
        }

    }

    @Override
    public List<ResponseFicheroGuestDTO> listaFicheroProfesional(String usuario, String especialidad) throws Exception {

        Optional<Profesional> busqueda = profesionalRepositorio.findByUsuario(usuario);
        List<ResponseFicheroGuestDTO> listaResponse = new ArrayList();

        if (busqueda.isPresent()) {
            Profesional profesional = busqueda.get();
            if (especialidad.equals("-")) {
                List<FicheroGuest> listaFichero = ficheroRepositorio.listaFicheroProfesional(usuario);
                for (FicheroGuest aux : listaFichero) {
                    ResponseFicheroGuestDTO response = new ResponseFicheroGuestDTO();

                    response.setFechaConsulta(aux.getFechaConsulta());
                    response.setObra_social(aux.getGuest().getObra_social());
                    response.setNombreGuest(aux.getGuest().getNombre());
                    response.setApellidoGuest(aux.getGuest().getApellido());
                    response.setDescripcion(aux.getDescripcion());
                    response.setDni(aux.getGuest().getDni());
                    listaResponse.add(response);
                }
                return listaResponse;
            } else {
                List<FicheroGuest> listaFichero = ficheroRepositorio.listaFicheroProfesionalEspecialidad(usuario, especialidad);
                for (FicheroGuest aux : listaFichero) {
                    ResponseFicheroGuestDTO response = new ResponseFicheroGuestDTO();

                    response.setFechaConsulta(aux.getFechaConsulta());
                    response.setObra_social(aux.getGuest().getObra_social());
                    response.setNombreGuest(aux.getGuest().getNombre());
                    response.setApellidoGuest(aux.getGuest().getApellido());
                    response.setDescripcion(aux.getDescripcion());
                    response.setDni(aux.getGuest().getDni());
                    listaResponse.add(response);
                }
                return listaResponse;
            }

        } else {
            throw new ResourceNotFoundException("no se encontró al usuario");
        }
    }

}
