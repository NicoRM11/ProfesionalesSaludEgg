/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.salud.repositorios;

import com.egg.salud.entidades.Guest;
import com.egg.salud.entidades.Oferta;
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
public interface OfertaRepositorio extends JpaRepository <Oferta, Long> {
    
    
    public Optional <Oferta> findById(Long id);
    
    public Optional<List<Oferta>> findByDisponible(Boolean disponible);
    
//    @Query("SELECT o FROM oferta o WHERE o.disponible = :disponible")
//    public List<Oferta> listarPorOfertaProfesionalAceptada(@Param("disponible") Boolean disponible);
}
