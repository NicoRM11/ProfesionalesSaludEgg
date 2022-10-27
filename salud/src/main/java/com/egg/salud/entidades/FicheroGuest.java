package com.egg.salud.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Data
public class FicheroGuest {

    private Long id;
    private String descripcion;
    private Date fechaConsulta;

    @ManyToOne
    private Guest guest;


}
