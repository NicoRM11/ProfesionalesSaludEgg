import { React } from 'react';
import { NavSesionAdmin } from './NavSesionAdmin';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';
import { useEffect } from 'react';
import axios from 'axios';
import { useState } from 'react';
import { Error404 } from './Error404';




export const ListarAdmin = () => {
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const rol = JSON.parse(localStorage.getItem('rol'))
    const [pacientes, setPacientes] = useState([]);
    const [profesionales, setProfesionales] = useState([]);

    useEffect(() => {
        cargarPacientes();
    }, []);


    const cargarPacientes = async () => {
        const URL = `http://localhost:8080/api/admin/listaUsuarios`;
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
                //response.data.password = `${password}`;
                setPacientes(response.data);
            }
        } catch (error) {
            console.log(error)
        }
    }

    useEffect(() => {
        cargarProfesionales();
    }, []);


    const cargarProfesionales = async () => {
        const URL = `http://localhost:8080/api/admin/listaProfesionales`;
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
                //response.data.password = `${password}`;
                setProfesionales(response.data);
            }
        } catch (error) {
            console.log(error)
        }
    }


    const handleBaja = async (usuario) => {
        
        const URL = `http://localhost:8080/api/profesional/${usuario.usuario}`;
        try {
            const response = await axios.delete(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 200) {
                cargarProfesionales()
            }
        } catch (error) {
            console.log(error)
        }

    }

    const handleAlta = async (usuario) => {
        
        const URL = `http://localhost:8080/api/admin/${usuario.usuario}`;
        try {
            const response = await axios.patch(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 200) {
                cargarProfesionales()
            }
        } catch (error) {
            console.log(error)
        }

    }

    const handleBajaPaciente = async (usuario) => {
        
        const URL = `http://localhost:8080/api/guest/${usuario.usuario}`;
        try {
            const response = await axios.delete(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 200) {
                cargarPacientes()
            }
        } catch (error) {
            console.log(error)
        }

    }

    const handleAltaPaciente = async (usuario) => {
        
        const URL = `http://localhost:8080/api/admin/${usuario.usuario}`;
        try {
            const response = await axios.patch(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            if (response.status === 200) {
                cargarPacientes()
            }
        } catch (error) {
            console.log(error)
        }

    }


    return (
        <>
        {rol==="ROLE_ADMIN" ?
        <>
        <nav className="navbar  navbar-expand-sm" >
                <div className="container-xxl">
                    <div className="navbar-brand mb-0 h1 text-white" href="#">
                        <Link to="/listar/usuarios"> <img src={logo} width="150" height="50" /> </Link>
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
                            <NavSesionAdmin data={pacientes}></NavSesionAdmin>
                        </ul>
                    </div>
                </div>
            </nav>
            <div className="row justify-content-center mt-5">
                <div className="col-11 text-center ">
                    <h1 className='titulosAdmin'>Listado Pacientes</h1>
                    <table className="table tabla rounded-2 text-white table-striped">
                        <thead >
                            <tr>
                                <th>ID</th>
                                <th>NOMBRE</th>
                                <th>APELLIDO</th>
                                <th>DNI</th>
                                <th>USUARIO</th>
                                <th>NACIONALIDAD</th>
                                <th>LOCALIDAD</th>
                                <th>TELEFONO</th>
                                <th>OBRA SOCIAL</th>
                                <th>ESTADO</th>
                                <th>MODIFICAR ESTADO</th>

                            </tr>
                        </thead>
                        <tbody >
                            {pacientes && pacientes.map((usuario) =>
                                <tr key={usuario.id}>
                                    <td className="text-white h6">{usuario.id}</td>
                                    <td className="text-white h6">{usuario.nombre}</td>
                                    <td className="text-white h6">{usuario.apellido}</td>
                                    <td className="text-white h6">{usuario.dni}</td>
                                    <td className="text-white h6">{usuario.usuario}</td>
                                    <td className="text-white h6">{usuario.nacionalidad}</td>
                                    <td className="text-white h6">{usuario.localidad}</td>
                                    <td className="text-white h6">{usuario.telefono}</td>
                                    <td className="text-white h6">{usuario.obra_social}</td>
                                    <td className="text-white h6">{usuario.estado === true ? "ALTA" : "BAJA"}</td>
                                    {usuario.estado === true ? 
                                    <td><button className="bi bi-person-dash-fill bg-white btn" onClick={()=>handleBajaPaciente(usuario)}></button></td>
                                    : 
                                    <td><button className="bi bi bi-person-plus-fill bg-white btn" onClick={()=>handleAltaPaciente(usuario)}></button></td>}
                                </tr>
                            )}
                        </tbody>

                    </table>
                </div>
            </div>

            <div className="row justify-content-center mt-5 ">
                <div className="col-11 text-center">
                <h1 className='titulosAdmin'>Listado Profesionales</h1>
                    <table className="table tabla rounded-2 text-white table-striped">
                        <thead >
                            <tr>
                                <th>ID</th>
                                <th>NOMBRE</th>
                                <th>APELLIDO</th>
                                <th>DNI</th>
                                <th>USUARIO</th>
                                <th>NACIONALIDAD</th>
                                <th>DOMICILIO</th>
                                <th>ESPECIALIDAD</th>
                                <th>MATRICULA</th>
                                <th>ESTADO</th>
                                <th>MODIFICAR ESTADO</th>
                            </tr>
                        </thead>
                        <tbody >
                            {profesionales && profesionales.map((usuario) =>
                                <tr key={usuario.id}>
                                    <td className="text-white h6">{usuario.id}</td>
                                    <td className="text-white h6">{usuario.nombre}</td>
                                    <td className="text-white h6">{usuario.apellido}</td>
                                    <td className="text-white h6">{usuario.dni}</td>
                                    <td className="text-white h6">{usuario.usuario}</td>
                                    <td className="text-white h6">{usuario.nacionalidad}</td>
                                    <td className="text-white h6">{usuario.domicilio}</td>
                                    <td className="text-white h6">{usuario.especialidad}</td>
                                    <td className="text-white h6">{usuario.matricula}</td>
                                    <td className="text-white h6">{usuario.estado === true ? "ALTA" : "BAJA"}</td>
                                    {usuario.estado === true ? 
                                    <td><button className="bi bi-person-dash-fill bg-white btn" onClick={()=>handleBaja(usuario)}></button></td>
                                    : 
                                    <td><button className="bi bi bi-person-plus-fill bg-white btn" onClick={()=>handleAlta(usuario)}></button></td>}
                                    
                                </tr>
                            )}
                        </tbody>

                    </table>
                </div>
            </div>
        </>    
        :
        <Error404></Error404>
    }
            
        </>
    )

}