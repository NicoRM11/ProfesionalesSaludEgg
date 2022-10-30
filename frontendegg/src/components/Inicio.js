import React from 'react'
import { Link, useLocation } from 'react-router-dom';
import logo from '../images/logo.png';
import { NavSesionGuest } from './NavSesionGuest';
import Carousel from 'react-bootstrap/Carousel';
import slide1 from '../images/slider1.png';
import slide2 from '../images/slider2.png';
import slide3 from '../images/slider3.jpg';
import Button from 'react-bootstrap/Button';
import { Col } from 'react-bootstrap/Col';
import { MisTurnos } from './MisTurnos';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';

const Inicio = () => {
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const [data,setdata] = useState({});

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

                    <div className="collapse navbar-collapse flex-row-reverse"
                        id="navbarnav">
                        <ul className="navbar-nav">
                            {/*location.pathname ==='/register' && <NavLogin></NavLogin>*/}
                            <NavSesionGuest data={data}></NavSesionGuest>
                        </ul>
                    </div>
                </div>
            </nav>

            <div className="mainContenedor mt-4 text-center">

                <Carousel>
                    <Carousel.Item>
                        <img
                            className="rounded-2"
                            height="500"
                            width="1200"
                            src={slide1}
                            alt="First slide"
                        />
                        <Carousel.Caption>
                            <h3>First slide label</h3>
                            <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="rounded-2"
                            height="500"
                            width="1200"
                            src={slide2}
                            alt="Second slide"
                        />

                        <Carousel.Caption>
                            <h3>Second slide label</h3>
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                        </Carousel.Caption>
                    </Carousel.Item>
                    <Carousel.Item>
                        <img
                            className="rounded-2"
                            height="500"
                            width="1200"
                            src={slide3}
                            alt="Third slide"
                        />

                        <Carousel.Caption>
                            <h3>Third slide label</h3>
                            <p>
                                Praesent commodo cursus magna, vel scelerisque nisl consectetur.
                            </p>
                        </Carousel.Caption>
                    </Carousel.Item>
                </Carousel>

                <div className='botonesI'>

                    <div>
                    <Link to="/MisTurnos">
                         <button className='botonI'>Mis Turnos</button>  
                    </Link>
                    
                    </div>

                    
                    <div className='boton2'>
                    <Link to="/Cartilla">
                         <button className='botonI'>Cartilla</button>  
                    </Link>
                    
                    </div>

                    

                </div>
            </div>
        </div>


    )
}

export default Inicio
