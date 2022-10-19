package com.egg.salud.dto;

import com.egg.salud.enumeraciones.Rol;
import lombok.Data;

@Data
public class ResponseUsuarioDTO {
    private String usuario;
    private Rol rol;
    private Boolean estado;
}
