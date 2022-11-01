package com.egg.salud.mapper;

import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.dto.ResponseUsuarioDTO;
import com.egg.salud.entidades.Usuario;
import com.egg.salud.enumeraciones.Rol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MapperAdmin {

    public Usuario map(RequestUsuarioDTO request) {

        Usuario u = new Usuario();
        u.setUsuario(request.getUsuario());
        u.setPassword(new BCryptPasswordEncoder().encode(request.getPassword()));
        u.setEstado(true);
        u.setRol(Rol.ADMIN);

        return u;
    }

    public ResponseUsuarioDTO map(Usuario u) {

        ResponseUsuarioDTO response = new ResponseUsuarioDTO();
        response.setUsuario(u.getUsuario());
        response.setRol(u.getRol());
        response.setEstado(u.getEstado());

        return response;
    }
    
    

    public List<ResponseUsuarioDTO> map(List<Usuario> lista) {

        List<ResponseUsuarioDTO> listaResponse = new ArrayList<>();

        for (Usuario aux : lista) {

            if (aux.getRol().toString().equals("ADMIN")) {
                listaResponse.add(map(aux));
            }
        }
        return listaResponse;

    }
    
    
    

}
