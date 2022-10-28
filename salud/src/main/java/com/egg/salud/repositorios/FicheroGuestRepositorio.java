package com.egg.salud.repositorios;

import com.egg.salud.entidades.FicheroGuest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FicheroGuestRepositorio extends JpaRepository <FicheroGuest , Long>{
    
    
}
