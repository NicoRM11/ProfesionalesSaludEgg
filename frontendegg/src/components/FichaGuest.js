import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import axios from 'axios';
import logo from '../images/logo.png';
import moment from 'moment';
import Swal from 'sweetalert2';
import { Link, useNavigate } from 'react-router-dom';
import lupa from '../images/lupa.png';

import Card from 'react-bootstrap/Card';
import { NavSesionGuest } from './NavSesionGuest';
import { Error404 } from './Error404';

export const FichaGuest = () => {
    const [fichas, setFichas] = useState([]);
    const [data,setdata] = useState({});
    const [especialidad, setEspecialidad] = useState({ especialidad: "-" });
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const rol = JSON.parse(localStorage.getItem('rol'))
    let navigate = useNavigate();

    useEffect(() => {
        cargarFichas();
    }, []);


    const cargarFichas = async () => {
        const URL = `http://localhost:8080/api/fichero/listaGuest/${username}/${especialidad.especialidad}`;
        try {
            const response = await axios.get(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 200) {
                setFichas(response.data);
                console.log(response);
            }
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: ` ${error.response.data.messages[0]}!`,
            })
            console.log(error)
        }
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        cargarFichas();
    }

    useEffect(() => {
        cargarPerfil();
    }, []);

    
    const cargarPerfil = async () => {
        const URL = `http://localhost:8080/api/guest/detalle/${username}`;
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
        {rol==="ROLE_GUEST" ? 
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
                            <NavSesionGuest data={data}></NavSesionGuest>
                        </ul>
                    </div>
                </div>
            </nav>

            <section className="container py-5">

                <div className="row justify-content-center align-items-center">

                    <Form className="Formulario col-md-10 col-xxl-10 py-2 rounded h6 text-white" onSubmit={handleSubmit}>
                    <h1 className="border-bottom text-center text-white mt-3 mb-5">Mis Fichas</h1>

                        <div className='px-5 '>
                            <Row className=" text-center">
                            <Col md={4}>
                            <Form.Label >Especialidad</Form.Label>
                            </Col>
                            </Row>
                            <Row className=" text-center">
                                <Col md={4}>
                                    
                                    <select className="form-select" value={especialidad.especialidad} name="especialidad" aria-label="Default select example" onChange={(e) => setEspecialidad({ ...especialidad, especialidad: e.target.value })}>
                                        <option value="-">Todas</option>
                                        <option value="Pediatria">Pediatria</option>
                                        <option value="Ginecologia">Ginecología</option>
                                        <option value="Clinica">Clinica</option>
                                        <option value="Cardiologia">Cardiologia</option>
                                    </select>
                                </Col>
                                <Col md={2} className='d-flex justify-content-end'>
                                    <button type="submit" className="btn btn-light">
                                        <img src={lupa} width="19" height="19" />
                                    </button>
                                </Col>

                            </Row>
                        </div>
                        <div className="card-group justify-content-around">
                            {
                                fichas && fichas.map(o =>

                                    <Row key={o.id} className='mt-5 mb-4 px-4 text-black'>
                                        <Col>
                                            <Card style={{ width: '15rem' }}>
                                                <Card.Body >
                                                    <Card.Title> DR.{o.nombreProfesional} {o.apellidoProfesional}</Card.Title>
                                                    <Card.Text>
                                                        Especialidad: {o.especialidad}
                                                    </Card.Text>
                                                    <Card.Text>
                                                        Descripcion: {o.descripcion}
                                                    </Card.Text>
                                                    <Card.Footer>
                                                        {moment(o.fechaConsulta).format('DD.MM.YYYY HH:mm')}
                                                    </Card.Footer>
                                                </Card.Body>
                                            </Card>
                                        </Col>
                                    </Row>
                                )
                            }
                        </div>
                    </Form>
                </div>
            </section>
        </>   
        :
        <Error404></Error404> 
    }
        </>
    )
}



