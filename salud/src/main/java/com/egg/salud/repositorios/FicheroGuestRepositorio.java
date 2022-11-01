package com.egg.salud.repositorios;

import com.egg.salud.entidades.FicheroGuest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface FicheroGuestRepositorio extends JpaRepository <FicheroGuest , Long>{
    
    
//    @Query("SELECT  w FROM ficheroGuest w WHERE w.profesional.usuario = :usuario AND w.id = :id " )
//    public Boolean profesionalFichero(@Param("usuario")String usuario,@Param("id") Long id);
    
    @Query("SELECT f FROM fichero_guest f WHERE f.guest = (SELECT u.id FROM usuario u WHERE u.usuario = :usuario)")
    public List<FicheroGuest> listaFicheroGuest(@Param("usuario") String usuario);
    
    @Query("SELECT f FROM fichero_guest f WHERE f.profesional = (SELECT u.id FROM usuario u WHERE u.usuario = :usuario)")
    public List<FicheroGuest> listaFicheroProfesional(@Param("usuario") String usuario);
    
    @Query("SELECT f FROM fichero_guest f WHERE f.profesional = (SELECT u.id FROM usuario u WHERE u.usuario = :usuario) AND f.profesional.especialidad = :especialidad")
    public List<FicheroGuest> listaFicheroProfesionalEspecialidad(@Param("usuario") String usuario , @Param("especialidad") String especialidad);


    @Query("SELECT f FROM fichero_guest f WHERE f.profesional.especialidad =:especialidad and f.guest.usuario =:usuario")
    public List<FicheroGuest> FicherosByUsuarioByEspecialidad(@Param("usuario") String usuario , @Param("especialidad") String especialidad);

    // @Query("SELECT IF( w FROM ficheroGuest w WHERE w.profesional.usuario = :usuario AND w.id = :id , 'TRUE' , 'FALSE')" )
}
