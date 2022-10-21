package com.egg.salud.entidades;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuest")
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesional", nullable = false)
    private Profesional profesional;

    
    //Revisar el Date para fecha y hora
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Temporal(TemporalType.TIME)
    private Date hora;
    
    private String localidad;
    private String ubicacion;
    private String modalidad;
    private String especialidad;
    private Integer telefono;
    private Boolean disponible;

}
