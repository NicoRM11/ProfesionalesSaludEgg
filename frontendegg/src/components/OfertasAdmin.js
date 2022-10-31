import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import DatePicker from "react-datepicker";
import Swal from 'sweetalert2';
import { CustomEvent } from './CustomEvent';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';
import logo from '../images/logo.png';
import { NavSesionAdmin } from './NavSesionAdmin';
import { Error404 } from './Error404';

require('moment/locale/es.js');

export const OfertasAdmin = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ start: null, end: null, consultorio: "", modalidad: "", telefono: "", localidad: "", disponible: true });
    const [allEvents, setAllEvents] = useState([]);
    const [estado, setEstado] = useState(1)

    
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
                title: `DR: ${event.profesional.nombre} ${event.profesional.apellido}\n
                Modalidad: ${event.modalidad}\n
            Telefono: ${event.telefono}\n
            Estado: ${event.disponible === false ? "oferta reservada" : "oferta disponible"}
            ----------------------\n
            Paciente:  --\n 
            Obra social: --`,
            })
        } else {
            Swal.fire({
                title: `DR: ${event.profesional.nombre} ${event.profesional.apellido}\n
                Modalidad: ${event.modalidad}\n
            Telefono: ${event.telefono}\n
            Estado: ${event.disponible === false ? "oferta reservada" : "oferta disponible"}
            ----------------------\n
            Paciente: ${event.guest.nombre ? event.guest.nombre : " --"} ${event.guest.apellido ? event.guest.apellido : " --"}\n 
            Obra social: ${event.guest.obra_social ? event.guest.obra_social : "--"}`,
            })
        }

    };

    const cargarOfertas = async () => {
        const URL = `http://localhost:8080/api/oferta/listarOfertas`;
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

    const [dataUsuario,setdata] = useState({});



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
                            <NavSesionAdmin data={dataUsuario}></NavSesionAdmin>
                        </ul>
                    </div>
                </div>
            </nav>


            <div className="container-xxl">
                <div className='calendario  mt-5'>
                <h1 className=" text-center text-white mt-4 mb-4">Calendario</h1>
                
                

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
