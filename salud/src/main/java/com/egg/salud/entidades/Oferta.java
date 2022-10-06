package com.egg.salud.entidades;

import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;

import lombok.Data;

@Entity
@Data
@AllArgsConstructor
public class Oferta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idGuest", nullable = false)
    private Guest id_user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idProfesional", nullable = false)
    private Profesional id_profesional;

    
    //Revisar el Date para fecha y hora
    
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Temporal(TemporalType.TIME)
    private Date hora;
    
    private String ubicacion;
    private String modalidad;
    private String especialidad;
    private Integer telefono;
    private Boolean estado;

}
