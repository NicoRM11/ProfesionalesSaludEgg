import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import DatePicker from "react-datepicker";
import Swal from 'sweetalert2';
import { CustomEvent } from './CustomEvent';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';
import { NavSesionProfesional } from './NavSesionProfesional';
import { Error404 } from './Error404';
import Localidades from './Localidades';


require('moment/locale/es.js');



export const OfertaProfesional = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ start: null, end: null, consultorio: "", modalidad: "", telefono: "", localidad: "", disponible: true });
    const [allEvents, setAllEvents] = useState([]);
    const [estado, setEstado] = useState(1)

    console.log(newEvent);

    
    const [data, setData] = useState({ start: "", end: "", consultorio: "", modalidad: "", telefono: "", localidad: "", guest: [] });
    const [selected, setSelected] = useState();
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const rol = JSON.parse(localStorage.getItem('rol'))


    let navigate = useNavigate();
    useEffect(() => {
        cargarOfertas();
    }, [estado])



    const handleSelected = (event) => {
        setSelected(event);
        if (event.disponible) {
            Swal.fire({
                title: `Modalidad: ${event.modalidad}\n
            Telefono: ${event.telefono}\n
            Estado: ${event.disponible === false ? "oferta reservada" : "oferta disponible"}
            ----------------------\n
            Paciente: ${event.guest.nombre ? event.guest.nombre : " --"} ${event.guest.apellido ? event.guest.apellido : " --"}\n 
            Obra social: ${event.guest.obra_social ? event.guest.obra_social : "--"}`,
                showCancelButton: true,
                confirmButtonColor: '#da1e31',
                confirmButtonText: 'Eliminar',
            }).then((result) => {
                if (result.isConfirmed) {
                    eliminarOferta(event);
                    Swal.fire('Oferta Eliminada!', '', 'success')
                }
            })
        } else {
            Swal.fire({
                title: `Modalidad: ${event.modalidad}\n
            Telefono: ${event.telefono}\n
            Estado: ${event.disponible === false ? "oferta reservada" : "oferta disponible"}
            ----------------------\n
            Paciente: ${event.guest.nombre ? event.guest.nombre : " --"} ${event.guest.apellido ? event.guest.apellido : " --"}\n 
            Obra social: ${event.guest.obra_social ? event.guest.obra_social : "--"}`,
                showCancelButton: true,
                denyButtonText: "Ver Fichas",
                denyButtonColor: "#1c86d1",
                showDenyButton: true,
                confirmButtonColor: '#da1e31',
                confirmButtonText: 'Eliminar',
            }).then((result) => {
                if (result.isConfirmed) {
                    eliminarOferta(event);
                    Swal.fire('Oferta Eliminada!', '', 'success')
                } else if (result.isDenied) {
                    const nombreApellido = `${event.guest.nombre} ${event.guest.apellido}`;
                    localStorage.setItem('nombreApellido', JSON.stringify(nombreApellido));
                    localStorage.setItem('especialidad', JSON.stringify(event.especialidad));
                    localStorage.setItem('paciente', JSON.stringify(event.guest.usuario));
                    localStorage.setItem('fechaConsulta', JSON.stringify(event.start));
                    navigate('/Ficha/lista/paciente');
                }
            })
        }

    };

    const eliminarOferta = async (event) => {
        const URL = `http://localhost:8080/api/oferta/${username}/${event.id}`;
        try {
            const response = await axios.delete(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            })

            if (response.status === 200) {
                Swal.fire('Oferta Eliminada!', '', 'success')
                setEstado(estado + 1);
            }

        } catch (error) {
            if (error.response.status === 406) {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: `No se pudo eliminar!`,
                })
            }
            console.log(error)
        }
    }


    const handleSubmit = async (e) => {
        e.preventDefault();
        const URL = `http://localhost:8080/api/oferta/crear-oferta/${username}`;
        setData({ start: newEvent.start, consultorio: newEvent.consultorio, end: newEvent.end, modalidad: newEvent.modalidad, telefono: newEvent.telefono, localidad: newEvent.localidad })
        console.log(newEvent);
        if (data.telefono !== "") {
            try {
                const response = await axios.post(URL, data, {
                    auth: {
                        username: `${username}`,
                        password: `${password}`
                    }
                })

                if (response.status === 201) {
                    Swal.fire(
                        'Excelente!',
                        'La oferta ha sido creada exitosamente',
                        'success'
                    )
                    setEstado(estado + 1);
                    //console.log(response)
                }

            } catch (error) {
                if (error.response.status === 406) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: `No se pudo crear, ${error.response.data} !`,
                    })
                }
                console.log(error)
            }
        }
    }

    const cargarOfertas = async () => {
        const URL = `http://localhost:8080/api/oferta/listar-todas-las-ofertas-profesional/${username}`;
        try {
            const response = await axios.get(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            }
            );
            console.log(response)
            if (response.status === 200) {
                response.data.map((oferta) => {
                    oferta.start = new Date(oferta.start);
                    oferta.end = new Date(oferta.end);
                    setNewEvent({ ...newEvent, oferta })
                })
                setAllEvents(response.data);
            }
        } catch (error) {
            console.log(error)
        }
    }

    
    useEffect(() => {
        cargarPerfil();
    }, []);

    const [dataUsuario,setdata] = useState({});

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
        } catch (error) {
            console.log(error)
        }
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
                            <NavSesionProfesional data={dataUsuario}></NavSesionProfesional>
                        </ul>
                    </div>
                </div>
            </nav>


            <div className="container-xxl">
                <div className='calendario  mt-5'>
                <h1 className=" text-center text-white mt-4 mb-4">Calendario</h1>
                <form onSubmit={handleSubmit}>
                    <div className="row justify-content-center">
                        <div className="col-md-2">
                            <input className="form-control rounded-2" required type="number" placeholder="Telefono" value={newEvent.telefono} onChange={(e) => setNewEvent({ ...newEvent, telefono: e.target.value })} />
                        </div>
                        <div className="col-md-2">
                            
                            <select className="form-select" value={newEvent.localidad} name="especialidad" aria-label="Default select example" onChange={(e) => setNewEvent({ ...newEvent, localidad: e.target.value })}>
                                <option value="-">Todas</option>
                                {Localidades.map((localidad, index) =>
                                    <option key={index} value={localidad}>{localidad}</option>
                                )}
                            </select>
                        </div>
                        <div className="col-md-2">
                            <input className="form-control rounded-2" type="text" placeholder="Consultorio" value={newEvent.consultorio} onChange={(e) => setNewEvent({ ...newEvent, consultorio: e.target.value })} />
                        </div>
                        <div className="col-md-2">
                            <select className="form-select" value={newEvent.modalidad} name="modalidad" onChange={(e) => setNewEvent({ ...newEvent, modalidad: e.target.value })}>
                                <option value="Modalidad">Modalidad</option>
                                <option value="Virtual">Virtual</option>
                                <option value="Presencial">Presencial</option>
                            </select>
                        </div>
                    </div>
                    <div className="row justify-content-center mt-2" style={{ position: "relative", zIndex: "999" }}>
                        <div className="col-md-2">
                            <DatePicker className="form-control rounded-2" required placeholderText="Start Date" dateFormat="MM/dd/yyyy HH:mm aa" showTimeSelect timeFormat="HH:mm" selected={newEvent.start} onChange={(start) => setNewEvent({ ...newEvent, start })} />

                        </div>
                        <div className="col-md-2">
                            <DatePicker className="form-control rounded-2" required tabindex="10" placeholderText="End Date" dateFormat="MM/dd/yyyy HH:mm aa" showTimeSelect timeFormat="HH:mm" selected={newEvent.end} onChange={(end) => setNewEvent({ ...newEvent, end })} />

                        </div>
                        <div className="col-md-2">
                            <button className="btn btn-success">
                                Crear Oferta
                            </button>
                        </div>
                        <div className="col-md-2"></div>
                    </div>
                </form>
                

                </div>
                <Calendar
                    localizer={localizer}
                    events={allEvents}
                    selected={selected}
                    onSelectEvent={handleSelected}
                    components={{ event: CustomEvent }}
                    startAccessor="start"
                    endAccessor="end"
                    style={{ height: 600, margin: "20px" }}
                    min={moment('2018-02-23 09:00:00').toDate()}
                    max={moment('2018-02-23 19:00:00').toDate()}
                    messages={{
                        next: "sig",
                        previous: "ant",
                        today: "Hoy",
                        month: "Mes",
                        week: "Semana",
                        day: "Día"
                    }}
                    eventPropGetter={(event) => {
                        const backgroundColor = event.disponible ? '#ec6434' : '#66bf4f';
                        return { style: { backgroundColor } }
                    }}
                />
            </div>
        </>
        :
        <Error404></Error404>
        }
            
        </>
    )
}
