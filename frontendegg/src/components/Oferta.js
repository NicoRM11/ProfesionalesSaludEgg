import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import DatePicker from "react-datepicker";
import Swal from 'sweetalert2';
import { CustomEvent } from './CustomEvent';
import axios from 'axios';
require('moment/locale/es.js');

/*const events = [
    {
        id: "1",
        modalidad: "Virtual",
        paciente: "Serena Williams", // recibir objeto guest
        especialidad: "Ginecologia",
        consultorio: "",
        localidad: "Escobar",
        telefono: "15122331",
        start: new Date(2022, 10, 1),
        end: new Date(2022, 10, 1),
        disponible: true
    },
    {
        id: "2",
        modalidad: "Virtual",
        paciente: "",
        especialidad: "Cardiologia",
        consultorio: "",
        localidad: "Escobar",
        telefono: "15122331",
        start: new Date(2022, 9, 7),
        end: new Date(2022, 9, 10),
        disponible: false
    },
    {
        id: "3",
        modalidad: "Presencial",
        paciente: "",
        especialidad: "Clinica",
        consultorio: "",
        localidad: "Escobar",
        telefono: "15884333",
        start: new Date(2022, 9, 20),
        end: new Date(2022, 9, 23),
        disponible: false
    },
];*/

export const Oferta = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ start:"", end:"",consultorio:"", modalidad:"", telefono:"", localidad:"", nombre:"",apellido:"",telfonoGuest:"" });
    const [allEvents, setAllEvents] = useState([]);
    const [data,setData] = useState({start:"", end:"",consultorio:"", modalidad:"", telefono:"", localidad:"", nombre:"",apellido:"",telfonoGuest:"" });
    const [selected, setSelected] = useState(); 
    console.log(data);
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))

    useEffect(() => {
        cargarOfertas();
    },[])
    const handleSelected = (event) => {
        setSelected(event);
        Swal.fire(`Modalidad: ${event.modalidad},\n
        Telefono: ${event.telefono}, \n
        Estado: ${event.disponible ? "oferta reservada": "oferta disponible"},\n
        Paciente: ${event.nombre ? event.nombre : " --"}`)
    };

    const URL = `http://localhost:8080/api/oferta/crear-oferta/${username}`;
    const handleSubmit = async(e) => {
        e.preventDefault();
        setAllEvents([...allEvents, newEvent]);
        setData({start:newEvent.start,consultorio:newEvent.consultorio, end:newEvent.end, modalidad:newEvent.modalidad, telefono:newEvent.telefono, localidad:newEvent.localidad})
        if(data.telefono!==""){
        try {
            const response = await axios.post(URL, data, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            })

            console.log(response);
            if (response.status === 200) {
                Swal.fire(
                    'Excelente!',
                    'La oferta ha sido creada exitosamente',
                    'success'
                )
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
            console.log(response);
            if (response.status === 200) {
                setAllEvents(response.data); 
            }
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <div className="myCustomHeight">
            <h1 className="text-center">Calendario</h1>
            <form onSubmit={handleSubmit}>
                <div className="row justify-content-center" style={{ position: "relative", zIndex: "999" }}>
                    <div className="col">
                        <input className="form-control rounded-2" required type="text" placeholder="Telefono" value={newEvent.telefono} onChange={(e) => setNewEvent({ ...newEvent, telefono: e.target.value })} />
                    </div>
                    <div className="col">
                        <input className="form-control rounded-2"  type="text" placeholder="Localidad" value={newEvent.localidad} onChange={(e) => setNewEvent({ ...newEvent, localidad: e.target.value })} />
                    </div>
                    <div className="col">
                        <input className="form-control rounded-2"  type="text" placeholder="Consultorio" value={newEvent.consultorio} onChange={(e) => setNewEvent({ ...newEvent, consultorio: e.target.value })} />
                    </div>
                    <div className="col">
                        <select className="form-select" value={newEvent.modalidad} name="modalidad" onChange={(e) => setNewEvent({ ...newEvent, modalidad: e.target.value })}>
                            <option value="Modalidad">Modalidad</option>
                            <option value="Virtual">Virtual</option>
                            <option value="Presencial">Presencial</option>
                        </select>
                    </div>
                    <div className="col">
                        <DatePicker className="form-control rounded-2" required placeholderText="Start Date" dateFormat="Pp" showTimeSelect selected={newEvent.start} onChange={(start) => setNewEvent({ ...newEvent, start })} />

                    </div>
                    <div className="col">
                        <DatePicker className="form-control rounded-2" required tabindex="10" placeholderText="End Date" dateFormat="Pp" showTimeSelect selected={newEvent.end} onChange={(end) => setNewEvent({ ...newEvent, end })} />

                    </div>
                    <div className="col-auto">
                        <button className="btn btn-success">
                            Crear Oferta
                        </button>
                    </div>
                </div>
            </form>
            <Calendar
                localizer={localizer}
                events={allEvents}
                selected={selected}
                onSelectEvent={handleSelected}
                components={{ event: CustomEvent }}
                startAccessor="start"
                endAccessor="end"
                style={{ height: 600, margin: "50px" }}
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
    )
}
