import React, { useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';
import { FormLabel } from 'react-bootstrap';


export const GuestProfile = () => {
    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "" });

    const handleChange = ({ target }) => {
        setdata({
            ...data,
            [target.name]: target.value
        })

    }


    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post(URL, data)
            console.log(response);
            if (response.status === 201) {
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
    return (

        <section className="container py-5">

            <div className="row justify-content-center align-items-center">

                <Form className="Formulario col-8 py-2 rounded h6 text-white" onSubmit={handleSubmit}>

                    <Row className="h2 text-center mt-4">
                        <Form.Label>Editar Perfil</Form.Label>
                    </Row>

                    <Row className="h2 text-center">

                        <div className="encabezado">

                            <div className='imagenGuest'>
                                <img src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRGqOlXhmKitASX-qEad_rY7QpUiJLD2GNjntA15AU&s" />
                            </div>

                            <div className='datosUsuario'>
                                <Row className="mb-4">

                                    <Col md={4}>
                                        <Form.Control type="text" name="nombre" placeholder="Nombre" required onChange={handleChange} />
                                    </Col>

                                    <Col md={6}>
                                        <Form.Control type="text" name="apellido" placeholder="Apellido" required onChange={handleChange} />
                                    </Col>

                                </Row>
                                <Row>

                                    <Col md={6}>
                                        <Form.Control type="number" name="dni" placeholder="DNI" required onChange={handleChange} disabled />
                                    </Col>
                                </Row>
                            </div>

                        </div>
                    </Row>

                    <div className='px-5'>

                        <Row className="mb-4">


                            <Col md={6}>
                                <Form.Label>Localidad</Form.Label>
                                <Form.Control type="text" name="localidad" placeholder="Localidad" required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            <Col md={4}>
                                <Form.Label>Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Contraseña" required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Obra Social</Form.Label>
                                <Form.Control type="text" name="obra_social" placeholder="Obra Social" required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            <Col md={4}>
                                <Form.Label>Nueva Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Nueva Contraseña" required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Telefono</Form.Label>
                                <Form.Control type="number" name="telefono" placeholder="Telefono" required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            {/*temporal*/}
                            <Col md={4}>
                                <Form.Label>Fecha de nacimiento</Form.Label>
                                <Form.Control type="date" name="fecha_nac" placeholder="Nacionalidad" required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Nacionalidad</Form.Label>
                                <Form.Control type="text" name="nacionalidad" placeholder="Nacionalidad" required onChange={handleChange} />
                            </Col>
                        </Row>
                    </div>
                    <Row className="mb-4">
                        <Button variant="" className="cta col-sm-3" type="submit">
                            <span>Guardar</span>
                            <svg viewBox="0 0 13 10" height="10px" width="15px">
                                <path d="M1,5 L11,5"></path>
                                <polyline points="8 1 12 5 8 9"></polyline>
                            </svg>
                        </Button>
                    </Row>
                </Form>
            </div>

            <div className='text-center mt-3'>
                <a className="darseDeBaja" href="#">Darse de baja
                </a>
            </div>
        </section>
    )
}



