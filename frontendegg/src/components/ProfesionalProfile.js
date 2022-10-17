import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';

export const ProfesionalProfile = () => {
    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", nacionalidad: "", apellido: "", dni: "", domicilio: "", especialidades: [], matriculas: [] });
    const [profesiones, setprofesiones] = useState([{ especialidad: "", matricula: "" }]);

    useEffect(() => {
        // cargarPerfil();
    }, []);

    const URL = "http://localhost:8080/api/profesional";
    const cargarPerfil = async () => {
        try {
            const response = await axios.get(URL, {
                params: {
                    usuario: data.usuario
                }
            })
            console.log(response);
        } catch (error) {
            if (error.response.status === 406) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `No se pudo cargar el perfil, ${error.response.data} !`,
                })
            }
            console.log(error)
        }
    }

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

    const handleProfesionAdd = () => {
        setprofesiones([...profesiones,
        { especialidad: "", matricula: ""}
        ])
    }

    const handleProfesionRemove = (index) => {
        const list = [...profesiones]
        list.splice(index, 1)
        setprofesiones(list)
    }

    const handleProfesionChange =(e,index) => {
        const{name,value} =e.target;
        const list = [...profesiones];
        list[index][name] = value;
        setprofesiones(list)
    }

    const addProfesionMatricula = () => { 
        const especialidades = profesiones.map(profesion=>profesion.especialidad);
        console.log(especialidades)
        const matriculas = profesiones.map(profesion=>profesion.matricula);
        setdata({...data,'especialidades':especialidades,'matriculas':matriculas});
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
                                        <Form.Control type="text" name="nombre" placeholder="Nombre" value={data.nombre} required onChange={handleChange} />
                                    </Col>

                                    <Col md={6}>
                                        <Form.Control type="text" name="apellido" placeholder="Apellido" value={data.apellido} required onChange={handleChange} />
                                    </Col>

                                </Row>
                                <Row>

                                    <Col md={6}>
                                        <Form.Control type="number" name="dni" placeholder="DNI" value={data.dni} required onChange={handleChange} disabled />
                                    </Col>
                                </Row>
                            </div>

                        </div>
                    </Row>

                    <div className='px-5'>

                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Domicilio</Form.Label>
                                <Form.Control type="text" name="domicilio" placeholder="Domicilio" value={data.domicilio} required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            <Col md={4}>
                                <Form.Label>Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Contraseña" value={data.password} required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Nacionalidad</Form.Label>
                                <Form.Control type="text" name="nacionalidad" placeholder="Nacionalidad" value={data.nacionalidad} required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            <Col md={4}>
                                <Form.Label>Nueva Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Nueva Contraseña" required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Fecha de nacimiento</Form.Label>
                                <Form.Control type="date" name="fecha_nac" value={data.fecha_nac} required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            {profesiones.map((p, index) => (
                                <div key={index} className="row">
                                    <Col md={3}>
                                        <Form.Label >Especialidad</Form.Label>
                                        <Form.Control type="text" name="especialidad" placeholder="Especialidad" value={p.especialidad} required onChange={(e) => handleProfesionChange(e, index)} />
                                    </Col>
                                    <Col md={3}>
                                        <Form.Label>Matricula</Form.Label>
                                        <Form.Control type="text" name="matricula" placeholder="Matricula" value={p.matricula} required onChange={(e) => handleProfesionChange(e, index)} />
                                    </Col>
                                    {
                                        profesiones.length - 1 === index && (
                                            <>
                                                <Col md={1} className="d-flex align-items-center">
                                                    <Button variant="" onClick={handleProfesionAdd} >
                                                        <i className="bi bi-plus-square-fill text-success"></i>
                                                    </Button>
                                                </Col>
                                            </>
                                        )
                                    }
                                    {
                                        profesiones.length > 1 && (
                                            <>
                                                <Col md={1} className="d-flex align-items-center">
                                                    <Button variant="" onClick={() => handleProfesionRemove(index)}>
                                                        <i className="bi bi-dash-circle-fill " width="30" ></i>
                                                    </Button>
                                                </Col>
                                            </>
                                        )
                                    }
                                </div>
                            ))}
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




