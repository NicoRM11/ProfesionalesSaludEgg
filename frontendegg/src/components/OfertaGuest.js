import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import Swal from 'sweetalert2';
import logo from '../images/logo.png';
import { CustomEvent } from './CustomEvent';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import { NavSesionGuest } from './NavSesionGuest';
import { Error404 } from './Error404';
require('moment/locale/es.js');

export const OfertaGuest = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ start: null, end: null, consultorio: "", modalidad: "", telefono: "", localidad: "", disponible: true });
    const [allEvents, setAllEvents] = useState([]);
    const [estado, setEstado] = useState(1);
    const [data, setdata] = useState({});

    const [selected, setSelected] = useState();
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    const profesional = JSON.parse(localStorage.getItem('profesional'))
    const rol = JSON.parse(localStorage.getItem('rol'))

    let navigate = useNavigate()

    useEffect(() => {
        cargarOfertas();
    }, [estado])

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

    const handleSelected = (event) => {
        setSelected(event);
        Swal.fire({
            title: ` Doctor: ${event.profesional.nombre ? event.profesional.nombre : " --"} ${event.profesional.apellido ? event.profesional.apellido : " --"}\n 
            Especialidad: ${event.especialidad ? event.especialidad : "--"}\n
            Modalidad: ${event.modalidad}\n
            ${event.modalidad === "Presencial" ? `Consultorio: ${event.consultorio}\n` : ""}
            Horario: ${moment(event.start).format('HH:mm')} hs\n
            Telefono: ${event.telefono}`,
            showCancelButton: true,
            cancelButtonText: "Salir",
            confirmButtonColor: 'success',
            confirmButtonText: 'Tomar turno',
        }).then((result) => {
            if (result.isConfirmed) {
                tomarOferta(event);

            }
        })
    };

    const tomarOferta = async (event) => {
        const URL = `http://localhost:8080/api/oferta/aceptar-oferta-guest/${username}/${event.id}`;
        try {
            const response = await axios.put(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            })
            if (response.status === 200) {
                Swal.fire('Turno tomado!', '', 'success')
                setEstado(estado + 1);
            }

        } catch (error) {

            console.log(error)
        }
    }

    const cargarOfertas = async () => {
        const URL = `http://localhost:8080/api/oferta/listarOfertasProfesional/${profesional}`;
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
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: ` ${error.response.data} !`,
            })
            navigate('/cartilla');
            console.log(error)
        }
    }


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

            <div className="container-xxl">
                <h1 className="text-center">Turnos disponibles</h1>
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
