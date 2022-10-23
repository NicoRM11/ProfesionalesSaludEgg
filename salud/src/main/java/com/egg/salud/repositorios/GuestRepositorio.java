/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.salud.repositorios;

import com.egg.salud.entidades.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author santi
 */
@Repository
public interface GuestRepositorio extends JpaRepository <Guest, Long>{

    public Optional<Guest> findByUsuario(String usuario);
    
    public Optional<List<Guest>> findByDni(Integer dni);

    public  Boolean existsByUsuario(String usuario);

    public Boolean existsByDni(Integer dni);

    @Query("SELECT g FROM guest g WHERE g.obra_social = :obra_social")
    public List<Guest> listaPorObraSocial(@Param("obra_social") String obra_social);
    
}