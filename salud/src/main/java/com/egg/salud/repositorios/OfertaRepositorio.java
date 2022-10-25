/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.egg.salud.repositorios;

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
    
    //@Query("SELECT o FROM oferta o WHERE o.profesional = :profesional")
    @Query("SELECT o FROM oferta o WHERE o.profesional = (SELECT u.id FROM usuario u WHERE u.usuario = :usuario)")
    //@Query("SELECT o FROM oferta o WHERE o.profesional.usuario = :usuario")
    public List<Oferta> listaPorProfesional(@Param("usuario") String usuario);
    
    @Query("SELECT o FROM oferta o WHERE o.guest.usuario = :usuario")
    public List<Oferta> listaPorGuest(@Param("usuario") String usuario);
    
    @Query("SELECT o FROM oferta o WHERE o.localidad = :localidad")
    public List<Oferta> buscarPorLocalidad(@Param("localidad") String localidad);
    
    //@Query("SELECT o FROM oferta o WHERE o.id_profesional = (SELECT u.id FROM usuario u WHERE u.usuario = :usuario)")
}
