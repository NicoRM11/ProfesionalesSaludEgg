/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.salud.repositorio;

import com.egg.salud.entidades.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author santi
 */
@Repository
public interface ProfesionalRepositorio extends JpaRepository <Profesional, Long> {
    
}
