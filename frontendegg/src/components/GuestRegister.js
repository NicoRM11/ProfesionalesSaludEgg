import React, { useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';

import { useNavigate } from 'react-router-dom';


export const GuestRegister = () => {
  const [perfil, setPerfil] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "" });
  let navigate = useNavigate();

  const handleChange = ({ target }) => {
    setPerfil({
      ...perfil,
      [target.name]: target.value
    })

  }
  const URL = "http://localhost:8080/api/guest/registrar";

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(URL, perfil)
      console.log(response);
      if (response.status === 200) {
        Swal.fire(
          'Registrado!',
          'El usuario ha sido guardado exitosamente',
          'success'
        )
        navigate('/login');
      }

    } catch (error) {
      if (error.response.status === 406 || error.response.status === 404) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: `No se pudo registrar, ${error.response.data.messages[0]} !`,
        })
      }
      console.log(error)
    }
  }
  return (
      <section className="container py-5">
        <div className="row justify-content-center align-items-center mainContenedor">
          <Form className="Formulario col-md-8 col-xxl-12 py-2 rounded h6 text-white" onSubmit={handleSubmit}>
            <Row className="h2 text-center">
              <Form.Label>INGRESE SUS DATOS</Form.Label>
            </Row>
            <Row className="mb-4">
              <Col md={3}>
                <Form.Label >Nombre</Form.Label>
                <Form.Control type="text" name="nombre" placeholder="Nombre" required onChange={handleChange} />
              </Col>
              <Col md={3}>
                <Form.Label>Apellido</Form.Label>
                <Form.Control type="text" name="apellido" placeholder="Apellido" required onChange={handleChange} />
              </Col>
              <Col md={1}></Col>
              <Col md={4}>
                <Form.Label>Email</Form.Label>
                <Form.Control type="email" name="usuario" placeholder="Email" required onChange={handleChange} />
              </Col>

            </Row>
            <Row className="mb-4">
              <Col md={6}>
                <Form.Label>DNI</Form.Label>
                <Form.Control type="number" name="dni" placeholder="DNI" required onChange={handleChange} />
              </Col>
              <Col md={1}></Col>
              <Col md={4}>
                <Form.Label>Localidad</Form.Label>
                <Form.Control type="text" name="localidad" placeholder="Localidad" required onChange={handleChange} />
              </Col>
            </Row>
            <Row className="mb-4">
              <Col md={6}>
                <Form.Label>Obra Social</Form.Label>
                <Form.Control type="text" name="obra_social" placeholder="Obra Social" required onChange={handleChange} />
              </Col>
              <Col md={1}></Col>
              <Col md={4}>
                <Form.Label>Contraseña</Form.Label>
                <Form.Control type="password" name="password" placeholder="Contraseña" required onChange={handleChange} />
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
              {/* <Col md={4}>
              <Form.Label>Repetir contraseña</Form.Label>
              <Form.Control type="password" placeholder="Contraseña" required onChange={handleChange} />
              {data.password !== value.password2 &&
                <p className="text-dark"> No coinciden las contraseñas.</p>
              }
            </Col>*/}
            </Row>
            <Row className="mb-4">
              <Col md={6}>
                <Form.Label>Nacionalidad</Form.Label>
                <Form.Control type="text" name="nacionalidad" placeholder="Nacionalidad" required onChange={handleChange} />
              </Col>
            </Row>
            <Row className="">
              <Button variant="" className="cta col-sm-3" type="submit">
                <span>Registrarse</span>
                <svg viewBox="0 0 13 10" height="10px" width="15px">
                  <path d="M1,5 L11,5"></path>
                  <polyline points="8 1 12 5 8 9"></polyline>
                </svg>
              </Button>
            </Row>
          </Form>
        </div>
      </section>
  )
}
