package com.egg.salud.entidades;



import javax.persistence.*;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;


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

    @ManyToOne
    @JoinColumn(name = "id_guest", nullable = true)
   //@OneToOne(mappedBy ="oferta", cascade=CascadeType.ALL)
    private Guest guest;

    @ManyToOne
    @JoinColumn(name = "id_profesional", nullable = false)
    //@OneToOne(mappedBy ="oferta", cascade=CascadeType.ALL)
    private Profesional profesional;

    
    //Revisar el Date para start y end

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone="UTC-3")
    private Timestamp start;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone="UTC-3")
    private Timestamp end;

    
    private String localidad;
    private String consultorio;
    private String modalidad;
    private String especialidad;
    private Long telefono;
    private Boolean disponible;
    private Boolean estado;

}
