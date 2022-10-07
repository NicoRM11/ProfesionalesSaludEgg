/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.salud.repositorios;

import com.egg.salud.entidades.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 *
 * @author santi
 */
@Repository
public interface GuestRepositorio extends JpaRepository <Guest, Long>{

    public Optional<Guest> findByEmail(String email);

    public Optional<Guest> findByUsernameOrEmail(String usuario,String email);

    public Optional<Guest> findByUsuario(String usuario);

    public  Boolean existsByUsuario(String usuario);

    public  Boolean existsByEmail(String email);


}
