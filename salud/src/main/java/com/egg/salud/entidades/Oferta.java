package com.egg.salud.entidades;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="oferta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_guest")
   //@OneToOne(mappedBy ="oferta", cascade=CascadeType.ALL)
    private Guest guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_profesional", nullable = false)
    //@OneToOne(mappedBy ="oferta", cascade=CascadeType.ALL)
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
    private Long telefono;
    private Boolean disponible;
    private Boolean estado;

}