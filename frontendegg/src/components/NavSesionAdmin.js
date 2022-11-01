import React, { useState, useEffect } from 'react'
import { Link, useNavigate } from 'react-router-dom'

import superAdmin from '../images/superAdmin.jpg';
import Avatar from "@mui/material/Avatar";




export const NavSesionAdmin = ({ data }) => {
    let navigate = useNavigate();
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))

    const handleLogout = () => {
        localStorage.setItem('rol', JSON.stringify(""));
        localStorage.setItem('usuario', JSON.stringify(""));
        localStorage.setItem('password', JSON.stringify(""));
        navigate('/inicio');
    }

    return (
        <div className="nav-item active d-flex flex-row align-items-center">
            {username !== "" ?
                <>
                    <div className="dropdown">

                        <button className="btn d-flex flex-row" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <Avatar className=""
                                alt="Imagen Perfil"
                                src={superAdmin}
                                sx={{ width: 40, height: 40 }}   
                            />
                            <i className="bi bi-caret-down-square m-2 text-secondary"></i>

                        </button>
                        
                        <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><Link to={'/Listar/usuarios'} className="dropdown-item h-3 ml-2 btn ">Listar Usuarios</Link></li>
                            <li><Link to={'/ofertas/listado'} className="dropdown-item h-3 ml-2 btn ">Calendario Ofertas</Link></li>
                            <li><hr className="dropdown-divider" /></li>
                            <li><button className="dropdown-item h-3 ml-2 btn " onClick={handleLogout}>Cerrar Sesion</button></li>
                        </ul>
                    </div>
                    <h6 className="text-white m-2 "> Hola, Admin!</h6>
                </>
                :
                <>
                    <div className="dropdown">
                        <button className="btn" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
                            <Avatar className=""
                                alt="Imagen Perfil"
                                src=""
                                sx={{ width: 40, height: 40 }}
                            />
                        </button>
                        <ul className="dropdown-menu" aria-labelledby="dropdownMenuButton1">
                            <li><Link to={'/login'} className=" dropdown-item h-3 ml-2  btn">Iniciar Sesion</Link></li>
                            <li><Link to={'/register'} className="dropdown-item h-3 ml-2 btn ">Registrarse</Link></li>
                        </ul>
                    </div>
                    <h6 className="text-white m-2 "> Bienvenido</h6>
                </>



            }






        </div>
    )
}