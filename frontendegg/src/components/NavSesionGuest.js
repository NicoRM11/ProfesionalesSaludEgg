import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'

import Row from 'react-bootstrap/Row';
import axios from 'axios';
import Avatar from "@mui/material/Avatar";
import { GuestProfile } from './GuestProfile';





export const NavSesionGuest = () => {

    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "", urlFoto: "" });
    
    const username = JSON.parse(localStorage.getItem('usuario'))
    const URL = `http://localhost:8080/api/guest/detalle/${username}`;

    useEffect(() => {
        cargarPerfil();
    }, []);
    const cargarPerfil = async () => {
        try {
            const response = await axios.get(URL, {
                auth: {
                    username: `${username}`,
                }
            }
            );
            console.log(response);
            if (response.status === 200) {
                setdata(response.data);
                
            }
        } catch (error) {
            console.log(error)
        }
        
    }





    return (
        <div className="nav-item active">

            <Row>

            <Link to={'/GuestProfile'} className="text-decoration-none ">
                
                <Avatar
                    alt="Imagen Perfil"
                    src={data.urlFoto}
                    sx={{ width: 50, height: 50 }}
                />
                    
                </Link>
                <span className="text-white"> Hola, {data.nombre}</span>
            </Row>


        </div>
    )
}
