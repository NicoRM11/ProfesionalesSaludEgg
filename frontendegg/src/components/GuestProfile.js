import React, { useEffect, useState } from 'react'
import Form from 'react-bootstrap/Form';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Button from 'react-bootstrap/Button';
import axios from 'axios';
import Swal from 'sweetalert2';


export const GuestProfile = () => {
    const [data, setdata] = useState({ usuario: "", password: "", fecha_nac: "", nombre: "", localidad: "", nacionalidad: "", apellido: "", telefono: "", obra_social: "", dni: "", urlFoto: "" });
    const [edicion, setEdicion] = useState(false);
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
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
            if(response.status===202) { 

        /* SET1*/ setdata(response.data); // response.data devuelve todos los valores de la base de datos
        /* SET2*/ setdata({...data, password: `${password}`});  //lueego de guardar los datos de la base quiero modificar solo el password    
            //resultado obtenido: se ejecuta solo el SET2 devolviendo data con atributos vacios con el password seteado nada mas
            //otro resutlado: se ejecuta SET1 pero el SET2 NO
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
            const response = await axios.put(URL, ()=> {
                setdata({...data, password: `${password}`});
                return data;
            })
            
            //console.log(response);
            if (response.status === 200) {
                Swal.fire(
                    'Excelente!',
                    'El usuario ha sido modificado exitosamente',
                    'success'
                )
            }

        } catch (error) {
            /*if (error.response.status === 406) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `No se pudo modificar, ${error.response.data} !`,
                })
            }*/
            console.log("error")
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
                <a className="darseDeBaja" href="#">Darse de baja
                </a>
            </div>
        </section>
    )
}



