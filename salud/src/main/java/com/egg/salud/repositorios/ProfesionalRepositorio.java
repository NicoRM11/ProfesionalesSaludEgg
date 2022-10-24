/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.salud.repositorios;

import com.egg.salud.entidades.Profesional;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author santi
 */
@Repository
public interface ProfesionalRepositorio extends JpaRepository <Profesional, Long> {
    
    public  Boolean existsByUsuario(String usuario);
    
    public Optional<List<Profesional>> findByDni(Integer dni);

    public Boolean existsByDni(Integer dni);
    
    public Optional<Profesional> findById(String usuario); 
    
    public Optional<Profesional> findByUsuario(String usuario);
    
    //public Optional<Profesional> findById(String usuario);

    @Query("SELECT p FROM profesional p WHERE p.especialidad = :especialidad")
    public List<Profesional> listaPorEspecialidad(@Param("especialidad") String especialidad);

}
