import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';
import { Link, useNavigate } from 'react-router-dom';
import lupa from '../images/lupa.png';
import logo from '../images/logo.png';
import Card from 'react-bootstrap/Card';
import Localidades from './Localidades';
import { NavSesionGuest } from './NavSesionGuest';
import { Error404 } from './Error404';

export const Cartilla = () => {
    const [filtro, setFiltro] = useState({ localidad: "-", especialidad: "-" });
    const [data, setdata] = useState({});
    const [profesionales, setProfesionales] = useState([]);
    const [edicion, setEdicion] = useState(false);
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const rol = JSON.parse(localStorage.getItem('rol'))

    let navigate = useNavigate()
    console.log(filtro);

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

    useEffect(() => {
        cargarProfesionales();
    }, []);
    const cargarProfesionales = async () => {
        const URL = `http://localhost:8080/api/oferta/listar/${filtro.localidad}/${filtro.especialidad}`
        try {
            const response = await axios.get(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 200) {
                setProfesionales(response.data);
                console.log(response);
            }
        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: ` ${error.response.data} !`,
            })
            console.log(error)
        }
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        cargarProfesionales();
    }

    const handleOferta = (profesional) => {
        localStorage.setItem('profesional', JSON.stringify(profesional));
        navigate('/Oferta/guest');
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
            <h1 className="border-bottom text-center text-white mt-3 mb-5">Cartilla Médica</h1>

                <div className='px-5'>
                    <Row className=" text-center mt-4">

                        <Col md={4}>
                            <Form.Label >Localidad</Form.Label>
                            {/* <Form.Control type="text" name="localidad" placeholder="Localidad"  onChange={(e) => setdata({ ...data, localidad: e.target.value })} /> */}

                            <select className="form-select" value={filtro.localidad} name="especialidad" aria-label="Default select example" onChange={(e) => setFiltro({ ...filtro, localidad: e.target.value })}>
                                <option value="-">Todas</option>
                                {Localidades.map((localidad, index) =>
                                    <option key={index} value={localidad}>{localidad}</option>
                                )}
                            </select>

                        </Col>

                        <Col md={4}>
                            <Form.Label >Especialidad</Form.Label>
                            <select className="form-select" value={filtro.especialidad} name="especialidad" aria-label="Default select example" onChange={(e) => setFiltro({ ...filtro, especialidad: e.target.value })}>
                                <option value="-">Todas</option>
                                <option value="Pediatria">Pediatria</option>
                                <option value="Ginecologia">Ginecología</option>
                                <option value="Clinica">Clinica</option>
                                <option value="Cardiologia">Cardiologia</option>
                            </select>
                        </Col>
                        <Col md={4} className="lupita ">
                            

                            <button type="submit" className="px-4 btn btn-light">
                                <img src={lupa} width="19" height="19" />
                            </button>
                        </Col>

                    </Row>
                </div>
                <div className="card-group justify-content-around">
                    {
                        profesionales && profesionales.map(p =>

                            <Row key={p.id} className='mt-5 mb-4 px-2 text-black '>
                                <Col>
                                    <Card style={{ width: '14rem' }}>
                                        <Card.Img variant="top" src={p.urlFoto} style={{ width: '14rem', height: '14rem' }} />
                                        <Card.Body >
                                            <Card.Title>{p.nombre} {p.apellido}</Card.Title>
                                            <Card.Text>
                                                Especialidad: {p.especialidad}
                                            </Card.Text>
                                            <Button variant="success" onClick={() => handleOferta(p.usuario)}>Ver Ofertas</Button>
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
    : <Error404></Error404>
    }    
        </>
    )
}

