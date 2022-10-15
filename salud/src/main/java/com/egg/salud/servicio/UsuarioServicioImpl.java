package com.egg.salud.servicio;

import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.dto.ResponseUsuarioDTO;
import com.egg.salud.entidades.Rol;
import com.egg.salud.entidades.Usuario;
import com.egg.salud.repositorios.RolRepositorio;
import com.egg.salud.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio{

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ResponseEntity<?> registrarAdmin(RequestUsuarioDTO request) {
        if (usuarioRepositorio.existsByUsuario(request.getUsuario())){
            return new ResponseEntity<>("el email de usuario ya existe", HttpStatus.NOT_ACCEPTABLE);
        } else {
            Usuario u = new Usuario();
            u.setUsuario(request.getUsuario());
            u.setPassword(passwordEncoder.encode(request.getPassword()));
            u.setEstado(true);
            Rol roles = rolRepositorio.findByNombre("ROLE_ADMIN").get();
            u.setRoles(Collections.singleton(roles));

            usuarioRepositorio.save(u);
            return new ResponseEntity<>("usuario registrado exitosamente" , HttpStatus.CREATED);
        }
    }

    @Transactional
    @Override
    public ResponseEntity<?> modificarAdmin(String request, Long id) {
        Optional<Usuario> busqueda = usuarioRepositorio.findById(id);
        if (busqueda.isPresent()){
            Usuario u = busqueda.get();
            u.setPassword(passwordEncoder.encode(request));
            usuarioRepositorio.save(u);

            return new ResponseEntity<>("usuario modificado con éxito" , HttpStatus.OK);
        }else{
            return new ResponseEntity<>("no se encontró el id de usuario" , HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<ResponseUsuarioDTO>> listarAdmin() {

        List<Usuario> listaUsuario = usuarioRepositorio.findAll();
        List<ResponseUsuarioDTO> listaResponse = new ArrayList<>();

        for (Usuario aux: listaUsuario) {
            ResponseUsuarioDTO response = new ResponseUsuarioDTO();
            response.setUsuario(aux.getUsuario());
            listaResponse.add(response);
        }
        return new ResponseEntity<>(listaResponse, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<?> eliminarAdmin(Long id) {
        Optional<Usuario> busqueda = usuarioRepositorio.findById(id);
        if (busqueda.isPresent()){
            Usuario u = busqueda.get();
            u.setEstado(false);
            usuarioRepositorio.save(u);
            return new ResponseEntity<>("usuario eliminado con éxito" , HttpStatus.OK);
        } else {
            return new ResponseEntity<>("no se encontró el id de usuario" , HttpStatus.NOT_FOUND);
        }
    }



}
