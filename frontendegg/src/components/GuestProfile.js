import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';

import Avatar from "@mui/material/Avatar";
import { storage } from "./firebase";
import { ref, uploadBytes, getDownloadURL } from "firebase/storage";
import IconButton from '@mui/material/IconButton';
import PhotoCamera from '@mui/icons-material/PhotoCamera';
import { NavSesionGuest } from './NavSesionGuest';
import { Error404 } from './Error404';





export const GuestProfile = () => {
    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "", urlFoto: "" });
    const [edicion, setEdicion] = useState(false);
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const rol = JSON.parse(localStorage.getItem('rol'))
    const [image, setImage] = useState(null);
    //const [url, setUrl] = useState(null);

    let navigate = useNavigate()
    console.log(data);
    console.log(image);

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
        const URL = `http://localhost:8080/api/guest/${username}`;
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
                    text: `No se pudo modificar !`,
                })
            
            console.log(error)
        }

    }

    const handleDelete = async (e) => {
        e.preventDefault();
        const URL = `http://localhost:8080/api/guest/${username}`;
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
    /*Imagenes*/

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
                    setdata({...data,urlFoto:url})
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
        localStorage.setItem('profesional', JSON.stringify(""));
        navigate('/inicio');
    }

   // console.log(image)
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
                                        <Form.Control type="number" name="dni" placeholder="DNI" value={data.dni} onChange={handleChange} disabled />
                                    </Col>
                                </Row>
                            </div>

                        </div>
                    </Row>

                    <div className='px-5'>

                        <Row className="mb-4">


                            <Col md={6}>
                                <Form.Label>Localidad</Form.Label>
                                <Form.Control type="text" name="localidad" placeholder="Localidad" value={data.localidad} required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            <Col md={4}>
                                <Form.Label>Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Contraseña" value={data.password} disabled onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Obra Social</Form.Label>
                                <Form.Control type="text" name="obra_social" placeholder="Obra Social" value={data.obra_social} required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            <Col md={4}>
                                <Form.Label>Cambiar Contraseña</Form.Label>
                                <Form.Control type="password" name="password" placeholder="Nueva Contraseña" onChange={handleChangeNewPassword} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Telefono</Form.Label>
                                <Form.Control type="number" name="telefono" placeholder="Telefono" value={data.telefono} required onChange={handleChange} />
                            </Col>
                            <Col md={1}></Col>
                            {/*temporal*/}
                            <Col md={4}>
                                <Form.Label>Fecha de nacimiento</Form.Label>
                                <Form.Control type="date" name="fecha_nac" value={data.fecha_nac} required onChange={handleChange} />
                            </Col>
                        </Row>
                        <Row className="mb-4">
                            <Col md={6}>
                                <Form.Label>Nacionalidad</Form.Label>
                                <Form.Control type="text" name="nacionalidad" placeholder="Nacionalidad" value={data.nacionalidad} required onChange={handleChange} />
                            </Col>
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



