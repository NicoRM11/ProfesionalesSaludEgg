import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import moment from 'moment';
import Swal from 'sweetalert2';
import { Link, useNavigate } from 'react-router-dom';
import lupa from '../images/lupa.png';

import Card from 'react-bootstrap/Card';

export const FichaGuest = () => {
    const [data, setdata] = useState([]);
    const [especialidad, setEspecialidad] = useState({especialidad:"-"});
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))

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
                setdata(response.data);
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

    return (

        <section className="container py-5">

            <div className="row justify-content-center align-items-center">

                <Form className="Formulario col-md-10 col-xxl-10 py-2 rounded h6 text-white" onSubmit={handleSubmit}>

                    <div className='px-5'>
                        <Row className=" text-center mt-4">

                            <Col md={4}>
                                <Form.Label >Especialidad</Form.Label>
                                <select className="form-select" value={especialidad} name="especialidad" aria-label="Default select example" onChange={(e) => setEspecialidad({ ...especialidad, especialidad: e.target.value })}>
                                    <option value="-">Todas</option>
                                    <option value="Pediatria">Pediatria</option>
                                    <option value="Ginecologia">Ginecología</option>
                                    <option value="Clinica">Clinica</option>
                                    <option value="Cardiologia">Cardiologia</option>
                                </select>
                            </Col>
                            <Col className='justify-content-sm-end'>

                                <button type="submit" className="px-4 btn btn-light">
                                    <img src={lupa} width="19" height="19" />
                                </button>
                            </Col>

                        </Row>
                    </div>
                    <div className="card-group">
                        {
                            data && data.map(o =>

                                <Row key={o.id} className='mt-5 mb-4 px-4 text-black'>
                                    <Col>
                                        <Card style={{ width: '14rem' }}>
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
    )
}



