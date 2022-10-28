import React, { useState, useEffect } from 'react'
import { Link } from 'react-router-dom'


import axios from 'axios';
import Avatar from "@mui/material/Avatar";
import { GuestProfile } from './GuestProfile';





export const NavSesionGuest = () => {

    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "", urlFoto: "" });
    
    const username = JSON.parse(localStorage.getItem('usuario'))
    const URL = `http://localhost:8080/api/guest/detalle/${username}`;


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
        <div className="nav-item active d-flex flex-row align-items-center">

            

            <Link to={'/GuestProfile'} className="text-decoration-none ">
                
                <Avatar className=""
                    alt="Imagen Perfil"
                    src={data.urlFoto}
                    sx={{ width: 40, height: 40 }}
                />
                    
                </Link>
                <h6 className="text-white m-2 "> Hola, {data.nombre}</h6>
            


        </div>
    )
}
