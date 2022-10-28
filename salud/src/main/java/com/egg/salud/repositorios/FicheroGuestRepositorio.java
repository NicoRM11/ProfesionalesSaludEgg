package com.egg.salud.repositorios;

import com.egg.salud.entidades.FicheroGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FicheroGuestRepositorio extends JpaRepository <FicheroGuest , Long>{
    
    
//    @Query("SELECT  w FROM ficheroGuest w WHERE w.profesional.usuario = :usuario AND w.id = :id " )
//    public Boolean profesionalFichero(@Param("usuario")String usuario,@Param("id") Long id);
    
    
    
    // @Query("SELECT IF( w FROM ficheroGuest w WHERE w.profesional.usuario = :usuario AND w.id = :id , 'TRUE' , 'FALSE')" )
}
