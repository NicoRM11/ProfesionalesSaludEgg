/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.egg.salud.repositorios;

import com.egg.salud.entidades.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author santi
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

    public Boolean existsByUsuario(String usuario);

    public Optional<Usuario> findByUsuario(String usuario);
    
    public boolean existsByPassword(String password);  

}
