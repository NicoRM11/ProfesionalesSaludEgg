import { React } from 'react';
import { NavSesionAdmin } from './NavSesionAdmin';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';




export const ListarAdmin = () => {
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const [data, setdata] = useState({});

    useEffect(() => {
        cargarPerfil();
    }, []);

    const URL = `http://localhost:8080/api/profesional/detalle/${username}`;
    const cargarPerfil = async () => {
        try {
            const response = await axios.get(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            console.log(response);
            if (response.status === 200) {
                response.data.password = `${password}`;
                setdata(response.data);
            }
        } catch (error) {
            console.log(error)
        }
    }





    return (
        <>
            <nav className="navbar  navbar-expand-sm" >
                <div className="container-xxl">
                    <div className="navbar-brand mb-0 h1 text-white" href="#">
                        <Link to="/inicio"> <img src={logo} width="150" height="50" /> </Link>
                    </div>

                    <button
                        type="button"
                        data-bs-toggle="collapse"
                        data-bs-target="#navbarnav"
                        className="navbar-toggler"
                        aria-controls="navbarnav"
                        aria-expanded="false"
                        aria-label="Toggle navigation"
                    >
                        <span className="navbar-toggler-icon"></span>
                    </button>

                    <div className="collapse navbar-collapse flex-row-reverse"
                        id="navbarnav">
                        <ul className="navbar-nav">
                            {/*location.pathname ==='/register' && <NavLogin></NavLogin>*/}
                            <NavSesionAdmin data={data}></NavSesionAdmin>
                        </ul>
                    </div>
                </div>
            </nav>
            <table>
                <thead>
                    <tr>
                        {/*ROL*/}
                        <th>Tipo</th>
                        {/*MAIL*/}
                        <th>Usuario</th>
                        <th>DNI</th>
                        <th>Localidad</th>
                    </tr>
                </thead>
                <tbody>
                    
                </tbody>
            </table>
        </>
    )

}