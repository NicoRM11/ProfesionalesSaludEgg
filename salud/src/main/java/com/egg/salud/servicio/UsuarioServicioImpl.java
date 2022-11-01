package com.egg.salud.servicio;

import com.egg.salud.dto.LoginDTO;
import com.egg.salud.dto.RequestUsuarioDTO;
import com.egg.salud.dto.ResponseGuestDTO;
import com.egg.salud.dto.ResponseProfesionalDTO;
import com.egg.salud.dto.ResponseUsuarioDTO;
import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Profesional;
import com.egg.salud.entidades.Usuario;
import com.egg.salud.exceptions.DataNotFoundException;
import com.egg.salud.exceptions.ResourceNotFoundException;
import com.egg.salud.exceptions.UserIsExistsException;
import com.egg.salud.mapper.MapperAdmin;
import com.egg.salud.mapper.MapperGuest;
import com.egg.salud.mapper.MapperProfesional;
import com.egg.salud.repositorios.GuestRepositorio;
import com.egg.salud.repositorios.ProfesionalRepositorio;
import com.egg.salud.repositorios.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class UsuarioServicioImpl implements UsuarioServicio, UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private MapperAdmin mapper;
    
    @Autowired
    private ProfesionalRepositorio profesionalRepositorio;
    
    @Autowired
    private GuestRepositorio guestRepositorio;
    
    @Autowired
    private MapperGuest mapperGuest;
    
    @Autowired
    private MapperProfesional mapperProfesional;

    @Transactional
    @Override
    public String registrarAdmin(RequestUsuarioDTO request) throws Exception {

        if (usuarioRepositorio.existsByUsuario(request.getUsuario())) {
            throw new UserIsExistsException("el email de usuario ya existe");
        } else {
            Usuario u = mapper.map(request);
            usuarioRepositorio.save(u);
            return "usuario registrado exitosamente";
        }
    }

    @Transactional
    @Override
    public String modificarAdmin(String request, String usuario) throws Exception {
        Optional<Usuario> busqueda = usuarioRepositorio.findByUsuario(usuario);
        if (busqueda.isPresent()) {
            Usuario u = busqueda.get();
            if (u.getEstado() == true) {
                u.setPassword(new BCryptPasswordEncoder().encode(request));
                usuarioRepositorio.save(u);

                return "usuario modificado con éxito";
            } else {
                throw new UserIsExistsException("no se puede modificar al usuario");
            }
        } else {
            throw new ResourceNotFoundException("no se encontró el email de usuario");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public List<ResponseUsuarioDTO> listarAdmin() {

        List<Usuario> listaUsuario = usuarioRepositorio.findAll();
        
        List<ResponseUsuarioDTO> listaResponse = mapper.map(listaUsuario);
        
        return listaResponse;
    }

    @Transactional
    @Override
    public String eliminarAdmin(String usuario) throws Exception {
        Optional<Usuario> busqueda = usuarioRepositorio.findByUsuario(usuario);
        if (busqueda.isPresent()) {
            Usuario u = busqueda.get();
            u.setEstado(false);
            usuarioRepositorio.save(u);
            return "usuario eliminado con éxito";
        } else {
            throw new ResourceNotFoundException("no se encontró el email de usuario");
        }
    }

    @Override
    public User login(LoginDTO login) throws Exception {

        Optional<Usuario> respuesta = usuarioRepositorio.findByUsuario(login.getUsuario());

        if (respuesta.isPresent()) {
            Usuario usuario = respuesta.get();
            if (usuario.getEstado()) {
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
                if (passwordEncoder.matches(login.getPassword(), usuario.getPassword())) {
                    User user = (User) loadUserByUsername(usuario.getUsuario());
                    return user;
                } else {
                    throw new UserIsExistsException("contraseña incorrecta");
                }
            } else {
                throw new UserIsExistsException("no puede ingresar un usuario dado de baja");
            }
        } else {
            throw new ResourceNotFoundException("el email ingresado no se encuentra registrado");
        }

    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositorio.findByUsuario(email).get();

        if (usuario != null) {

            //lista de permisos
            List<GrantedAuthority> permisos = new ArrayList();

            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());

            permisos.add(p);

            //recuperar sesion de usuario ya logueado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);

            return new User(usuario.getUsuario(), usuario.getPassword(), permisos);

        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseUsuarioDTO buscarUsuario(String usuario) throws Exception {

        Optional<Usuario> busqueda = usuarioRepositorio.findByUsuario(usuario);

        if (busqueda.isPresent()) {
            Usuario u = busqueda.get();
            ResponseUsuarioDTO response = mapper.map(u);
            return response;
        } else {
            throw new ResourceNotFoundException("el email ingresado no se encuentra");
        }

    }

    @Override
    @Transactional
    public String altaUsuario(String usuario) throws Exception {

        Optional<Usuario> busqueda = usuarioRepositorio.findByUsuario(usuario);

        if (busqueda.isPresent()) {
            Usuario u = busqueda.get();
            u.setEstado(true);
            usuarioRepositorio.save(u);
            return "usuario dado de alta con exito";
        } else {
            throw new ResourceNotFoundException("el email ingresado no se encuentra");
        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseGuestDTO> listarGuest() {
        
        List<Guest> listaGuest = guestRepositorio.findAll();
        
        List<ResponseGuestDTO> listaResponse = new ArrayList();
        
        
        if (listaGuest.size() < 1) {
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }
        
        List<ResponseGuestDTO> lista = mapperGuest.map(listaGuest);
        
        return lista;
        
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<ResponseProfesionalDTO> listarProfesional() {

        List<Profesional> listarProfesional = profesionalRepositorio.findAll();

        if (listarProfesional.size() < 1) {
            throw new DataNotFoundException("no se encuentran registros en la base de datos");
        }

        List<ResponseProfesionalDTO> listaProfesionalDto = mapperProfesional.map(listarProfesional);

        return listaProfesionalDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ResponseUsuarioDTO> listaUsuariosCompleta() throws Exception {
       
        List<Usuario> listaUsuario = usuarioRepositorio.findAll();
        
        List<ResponseUsuarioDTO> listaResponse = new ArrayList();
        
        for (Usuario aux : listaUsuario) {
            ResponseUsuarioDTO response = new ResponseUsuarioDTO();
            response.setEstado(aux.getEstado());
            response.setRol(aux.getRol());
            response.setUsuario(aux.getUsuario());
            listaResponse.add(response);
        }
        return listaResponse;
    }
    
    

    
    
    
    
}


