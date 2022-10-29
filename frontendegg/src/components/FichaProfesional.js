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

export const FichaProfesional = () => {
    const [data, setdata] = useState([]);
    const [descripcion, setDescripcion] = useState({ descripcion: '' });
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const especialidad = JSON.parse(localStorage.getItem('especialidad'))
    const paciente = JSON.parse(localStorage.getItem('paciente'))
    const nombreApellido = JSON.parse(localStorage.getItem('nombreApellido'))
    let navigate = useNavigate();

    useEffect(() => {
        cargarFichas();
    }, []);


    const cargarFichas = async () => {
        const URL = `http://localhost:8080/api/fichero/lista/guest/${paciente}/${especialidad}`;
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
        crearFicha(e)
        
    }

    const crearFicha = async () => {
        const URL = `http://localhost:8080/api/fichero/crear-ficha/${username}/${paciente}`;
        try {
            const response = await axios.post(URL,descripcion, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            console.log(response);
            if (response.status === 200) {
                //setdata(response.data);
                cargarFichas();
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

    return (

        <section className="container py-5">

            <div className="row justify-content-center align-items-center">

                <Form className="Formulario col-md-10 col-xxl-10 py-2 rounded h6 text-white" onSubmit={handleSubmit}>

                    <div className="row">
                    <div className="col-md-2 text-center">
                            <label >Paciente</label>
                            <input className="form-control rounded-2" value={nombreApellido} type="text" placeholder="Nombre" disabled />
                        </div>
                        <div className="col-md-6">
                            <textarea className="form-control rounded-2" required type="text" placeholder="Descripcion" value={descripcion.descripcion} onChange={(e) => setDescripcion({ ...descripcion, descripcion: e.target.value })} />
                        </div>
                        <div className="col-md-2">
                            <button className="btn btn-success" type="submit">Crear Ficha</button>
                        </div>
                    </div>
                    <div className="card-group">


                        {
                            data && data.map(o =>

                                <Row key={o.id} className='mt-5 mb-4 px-4 text-black'>
                                    <Col>
                                        <Card style={{ width: '14rem' }}>
                                            <Card.Body >
                                                <Card.Title> {o.nombreGuest} {o.apellidoGuest}</Card.Title>
                                                <Card.Text>
                                                    Obra social: {o.obra_social}
                                                </Card.Text>
                                                <Card.Text>
                                                    DNI: {o.dni}
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



