import React from 'react'
import { Link, useLocation } from 'react-router-dom';
import logo from '../images/logo.png';
import { NavSesionGuest } from './NavSesionGuest';
import Carousel from 'react-bootstrap/Carousel';
import slide1 from '../images/slide1.png';
import slide2 from '../images/slide2.png';
import slide3 from '../images/slide3.png';
import Button from 'react-bootstrap/Button';
import { Col } from 'react-bootstrap/Col';
import { MisTurnos } from './MisTurnos';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';

const Inicio = () => {
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const [data, setdata] = useState({});

    useEffect(() => {
        cargarPerfil();
    }, []);

    const URL = `http://localhost:8080/api/guest/detalle/${username}`;
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


        <div>
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

                    <div className="text-white h6 collapse navbar-collapse flex-row-reverse"
                        id="navbarnav">


                            <ul className="navbar-nav">
                                {/*location.pathname ==='/register' && <NavLogin></NavLogin>*/}
                                <NavSesionGuest data={data}></NavSesionGuest>
                            </ul>


                        </div>
                </div>
            </nav>

            <div className="mainContenedor mt-4 text-center">


                <div className='presentacion'>

                    <h1 className='text-white'>Profesionales de la Salud</h1>
                    <p className='text-white'>Colectivo de Profesionales de la Salud auto-organizados</p>
                    <div className='carousel'>
                        <Carousel >
                            <Carousel.Item>
                                <img
                                    className="rounded-2"
                                    height="500"
                                    width="1100"
                                    src={slide1}
                                    alt="First slide"
                                />
                                <Carousel.Caption>
                                    <h3 className="text-white">Gestion online de turnos</h3>
                                    <p className="text-white">Turnos con modalidad online y presencial a un click.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    className="rounded-2"
                                    height="500"
                                    width="1100"
                                    src={slide2}
                                    alt="Second slide"
                                />

                                <Carousel.Caption>
                                    <h3 className="text-white">Todos los especialistas que necesita en un solo lugar</h3>
                                    <p className="text-white">Contamos con los mejores profesionales de la zona.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    className="rounded-2"
                                    height="500"
                                    width="1100"
                                    src={slide3}
                                    alt="Third slide"
                                />

                                <Carousel.Caption>
                                    <h3 className="text-white">El mejor equipo de trabajo</h3>
                                    <h6 className="text-white">
                                        Contamos con evaluaciones de satisfaccion de cada personal para poder asegurarnos que reciba la mejor atencion.
                                    </h6>
                                </Carousel.Caption>
                            </Carousel.Item>
                        </Carousel>

                    </div>

                </div>


                {username !== "" ? <div className='botonesI'>
                    <div>
                        <Link to="/MisTurnos">
                            <button className='botonI'>Mis Turnos</button>
                        </Link>
                    </div>

                    <div className='boton2'>
                        <Link to="/cartilla">
                            <button className='botonI'>Cartilla</button>
                        </Link>
                    </div>
                </div>
                    : <></>}

            </div>
        </div>


    )
}

export default Inicio
