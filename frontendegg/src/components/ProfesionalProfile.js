import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';
import { Link , useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';

import Avatar from "@mui/material/Avatar";
import { storage } from "./firebase";
import { ref, uploadBytes, getDownloadURL } from "firebase/storage";
import IconButton from '@mui/material/IconButton';
import PhotoCamera from '@mui/icons-material/PhotoCamera';
import { NavSesionProfesional } from './NavSesionProfesional';
import { Error404 } from './Error404';

export const ProfesionalProfile = () => {
    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", nacionalidad: "", apellido: "", dni: "", domicilio: "", especialidad: "", matricula: "" });
    const [edicion, setEdicion] = useState(false);
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const rol = JSON.parse(localStorage.getItem('rol'))
    let navigate = useNavigate();
    const [image, setImage] = useState(null);

    useEffect(() => {
        cargarPerfil();
    }, []);

    console.log(data);
    console.log(image);
    
    const cargarPerfil = async () => {
        const URL = `http://localhost:8080/api/profesional/detalle/${username}`;
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
            console.log(response);
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

    const handleSubmit = async (e) => {
        const URL = `http://localhost:8080/api/profesional/${username}`;
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
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: `No se pudo modificar!`,
            })
            console.log(error)
        }
    }
    const handleChangeNewPassword = ({ target }) => {
        setEdicion(true);
        setdata({
            ...data,
            password: target.value
        })
    }
    const handleDelete = async (e) => {
        e.preventDefault();
        const URL = `http://localhost:8080/api/profesional/${username}`;
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
                handleLogout();
            }

        } catch (error) {
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: `No se pudo eliminar la cuenta, intente nuevamente !`,
            })
            console.log(error)
        }
    }

    const handleImageChange = (e) => {

        if (e.target.files[0]) {
            setImage(e.target.files[0]);
        }
    }

    const handleSubmitImage = () => {

        /* Imagen */

        const imageRef = ref(storage, `image/${username}`);
        uploadBytes(imageRef, image)
            .then(() => {
                getDownloadURL(imageRef).then((url) => {
                    setdata({ ...data, urlFoto: url })
                    //setUrl(url)
                    //console.log(url);
                })
                    .catch(error => {
                        console.log(error.message, "error getting the image url");
                    });

                setImage(null);
            })
            .catch(error => {
                console.log(error.message);
            })
        setEdicion(true);
    }

    const handleLogout = () => {
        localStorage.setItem('rol', JSON.stringify(""));
        localStorage.setItem('usuario', JSON.stringify(""));
        localStorage.setItem('password', JSON.stringify(""));
        localStorage.setItem('nombreApellido', JSON.stringify(""));
        localStorage.setItem('especialidad', JSON.stringify(""));
        localStorage.setItem('fechaConsulta', JSON.stringify(""));
        localStorage.setItem('paciente', JSON.stringify(""));
        navigate('/inicio');
    }
    return (
        <>
        {rol==="ROLE_PROFESIONAL" ? 
        <>
        <nav className="navbar  navbar-expand-sm" >
        <div className="container-xxl">
            <div className="navbar-brand mb-0 h1 text-white" href="#">
                <Link to="/inicio/Profesional"> <img src={logo} width="150" height="50" /> </Link>
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
                    <NavSesionProfesional data={data}></NavSesionProfesional>
                </ul>
            </div>
        </div>
    </nav>


        <section className="container py-5">

            <div className="row justify-content-center align-items-center">

                <Form className="Formulario col-8 py-2 rounded h6 text-white" onSubmit={handleSubmit}>

                    <Row className="h2 text-center mt-4">
                        <Form.Label>Mi Perfil</Form.Label>
                    </Row>

                    <Row className="h2 text-center">

                        <div className="encabezado mb-5">

                            <div className='imagenGuest '>
                                <Avatar
                                    alt="Imagen Perfil"
                                    src={data.urlFoto}
                                    sx={{ width: 150, height: 150 }}
                                />
                                <div className='botones mt-2'>

                                    <IconButton color="primary" aria-label="upload picture" component="label" onChange={handleImageChange}>
                                        <input hidden accept="image/*" type="file" />
                                        <PhotoCamera color="success" />
                                    </IconButton>

                                    <Button variant="outline-success" onClick={handleSubmitImage}>Subir imagen</Button>{' '}

                                </div>

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
                                <Form.Label>Cambiar Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Nueva Contraseña" onChange={handleChangeNewPassword} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Fecha de nacimiento</Form.Label>
                                <Form.Control type="date" name="fecha_nac" value={data.fecha_nac} required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <div className="row">
                                <Col md={4}>
                                    <Form.Label >Especialidad</Form.Label>
                                    <select className="form-select" value={data.especialidad} name="especialidad" aria-label="Default select example" onChange={handleChange}>
                                        <option value="Pediatria">Pediatria</option>
                                        <option value="Ginecologia">Ginecología</option>
                                        <option value="Clinica">Clinica</option>
                                        <option value="Cardiologia">Cardiologia</option>
                                    </select>
                                </Col>
                                <Col md={3}>
                                    <Form.Label>Matricula</Form.Label>
                                    <Form.Control type="text" name="matricula" placeholder="Matricula" value={data.matricula} required onChange={handleChange} />
                                </Col>

                            </div>
                        </Row>
                    </div>
                    <Row className="mb-4">
                        {edicion === true && <Button variant="" className="cta col-sm-3" type="submit">
                            <span>Guardar</span>
                            <svg viewBox="0 0 13 10" height="10px" width="15px">
                                <path d="M1,5 L11,5"></path>
                                <polyline points="8 1 12 5 8 9"></polyline>
                            </svg>
                        </Button>}
                    </Row>
                </Form>
            </div>

            <div className='text-center mt-3'>
                <button className="btn darseDeBaja" onClick={handleDelete}>Darse de baja
                </button>
            </div>
        </section>
        </>
        :
        <Error404></Error404>
        }
        
        </>
    )
}




