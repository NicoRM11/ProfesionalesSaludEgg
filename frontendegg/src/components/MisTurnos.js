import React, { useEffect, useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import Swal from 'sweetalert2';
import { CustomEvent } from './CustomEvent';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
require('moment/locale/es.js');

export const MisTurnos = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ start: null, end: null, consultorio: "", modalidad: "", telefono: "", localidad: "", disponible: true });
    const [allEvents, setAllEvents] = useState([]);
    const [estado, setEstado] = useState(1)

    const [selected, setSelected] = useState();
    const username = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))


    useEffect(() => {
        cargarOfertas();
    }, [estado])

    const handleSelected = (event) => {
        setSelected(event);
        Swal.fire({
            title: `Modalidad: ${event.modalidad}\n
        Telefono: ${event.telefono}\n
        ----------------------\n
        `,
            showCancelButton: true,
            confirmButtonColor: 'success',
            confirmButtonText: 'Cancelar turno',
        }).then((result) => {
            if (result.isConfirmed) {
                cancelarOferta(event);

            }
        })
    };

    const cancelarOferta = async (event) => {
        const URL = `http://localhost:8080/api/oferta/cancelar-oferta-guest/${username}/${event.id}`;
        try {
            const response = await axios.delete(URL, {
                auth: {
                    username: `${username}`,
                    password: `${password}`
                }
            })
            if (response.status === 200) {
                Swal.fire('Turno cancelado!', '', 'success')
                setEstado(estado + 1);
            }

        } catch (error) {

            console.log(error)
        }
    }

    const cargarOfertas = async () => {
        const URL = `http://localhost:8080/api/oferta/listar-ofertas-guest/${username}`;
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
            console.log(error)
        }
    }


    return (
        <div className="container-xxl">
            <h1 className="text-center">Mis Turnos</h1>
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
    )
}
