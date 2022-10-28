package com.egg.salud.entidades;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.sql.Timestamp;
import lombok.Data;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "fichero_guest")
@AllArgsConstructor
@NoArgsConstructor
public class FicheroGuest {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ",timezone="UTC-3")
    private Timestamp fechaConsulta;

    @ManyToOne
    @JoinColumn(name = "id_guest", nullable = true)
    private Guest guest;
    
    @ManyToOne
    @JoinColumn(name = "id_profesional", nullable = true)
    private Profesional profesional;
    

    private Boolean estado;

}
