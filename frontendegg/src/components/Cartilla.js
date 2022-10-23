import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';
import { useNavigate } from 'react-router-dom';
import lupa from '../images/lupa.png';



import Card from 'react-bootstrap/Card';
import { height } from '@mui/system';
import { imageListClasses } from '@mui/material';







export const Cartilla = () => {
    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "", urlFoto: "" });
    const [edicion, setEdicion] = useState(false);
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const [image, setImage] = useState(null);
    const [url, setUrl] = useState(null);

    let navigate = useNavigate()
    console.log(data);

    useEffect(() => {
        cargarPerfil();
    }, []);

    const URL = `http://localhost:8080/api/guest/${username}`;
    const cargarPerfil = async () => {
        try {
            const response = await axios.get(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 202) {
                response.data.password = `${password}`;
                setdata(response.data);
            }
        } catch (error) {
            console.log(error)
        }
    }



    const handleChange = ({ target }) => {
        setEdicion(true);
        setdata({
            ...data,
            [target.name]: target.value
        })
    }
    const handleChangeNewPassword = ({ target }) => {
        setEdicion(true);
        setdata({
            ...data,
            password: target.value
        })
    }
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.put(URL, data, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            })

            console.log(response);
            if (response.status === 200) {
                Swal.fire(
                    'Excelente!',
                    'El usuario ha sido modificado exitosamente',
                    'success'
                )
            }

        } catch (error) {
            if (error.response.status === 406) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `No se pudo modificar, ${error.response.data} !`,
                })
            }
            console.log(error)
        }

    }

    const handleDelete = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.delete(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            })

            console.log(response);
            if (response.status === 200) {
                Swal.fire(
                    'Se ha dado de baja exitosamente!',
                    'Lo vamos a extrañar',
                    'success'
                )
                navigate('/login');
            }

        } catch (error) {
            if (error.response.status === 406) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `No se pudo eliminar la cuenta, ${error.response.data} !`,
                })
            }
            console.log(error)
        }
    }

    console.log(image)
    return (

        <section className="container py-5">

            <div className="row justify-content-center align-items-center">

                <Form className="Formulario col-8 py-2 rounded h6 text-white" onSubmit={handleSubmit}>

                    <div className='px-5'>
                        <Row className="h2 text-center mt-4">

                            <Col md={4}>
                                <Form.Control type="text" name="Localidad" placeholder="Localidad" required onChange={handleChange} />
                            </Col>

                            <Col md={4}>
                                <Form.Control type="text" name="Especialidad" placeholder="Especialidad" required onChange={handleChange} />
                            </Col>


                            <Col className='justify-content-sm-end'>

                                <button type="button" className="px-4 btn btn-light">
                                    <img src={lupa} width="19" height="19" />
                                </button>
                            </Col>

                        </Row>
                    </div>

                    <Row className='mt-5 mb-4 px-4'>
                        <Col>
                        <Card style={{ width: '14rem' }}>
                                <Card.Img variant="top" src="" />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the
                                        bulk of the card's content.
                                    </Card.Text>
                                    <Button variant="primary">Go somewhere</Button>
                                </Card.Body>
                            </Card>

                        </Col>
                        <Col>
                            <Card style={{ width: '14rem' }}>
                                <Card.Img variant="top" src="" />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the
                                        bulk of the card's content.
                                    </Card.Text>
                                    <Button variant="primary">Go somewhere</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card style={{ width: '14rem' }}>
                                <Card.Img variant="top" src="holder.js/100px180" />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the
                                        bulk of the card's content.
                                    </Card.Text>
                                    <Button variant="primary">Go somewhere</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                    <Row className='mt-5 mb-4 px-4'>
                        <Col>
                            <Card style={{ width: '14rem' }}>
                                <Card.Img variant="top" src="holder.js/100px180" />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the
                                        bulk of the card's content.
                                    </Card.Text>
                                    <Button variant="primary">Go somewhere</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card style={{ width: '14rem' }}>
                                <Card.Img variant="top" src="holder.js/100px180" />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the
                                        bulk of the card's content.
                                    </Card.Text>
                                    <Button variant="primary">Go somewhere</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                        <Col>
                            <Card style={{ width: '14rem' }}>
                                <Card.Img variant="top" src="" />
                                <Card.Body>
                                    <Card.Title>Card Title</Card.Title>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the
                                        bulk of the card's content.
                                    </Card.Text>
                                    <Button variant="primary">Go somewhere</Button>
                                </Card.Body>
                            </Card>
                        </Col>
                    </Row>
                </Form>
            </div>


        </section>
    )
}

