import React, { useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import DatePicker from "react-datepicker";
import Swal from 'sweetalert2';
import { CustomEvent } from './CustomEvent';
require('moment/locale/es.js');

const events = [
    {
        id: "1",
        modalidad: "Virtual",
        paciente: "Serena Williams",
        especialidad: "Ginecologia",
        localidad: "Escobar",
        start: new Date(2022, 10, 1),
        end: new Date(2022, 10, 1),
        isBooked: true
    },
    {
        id: "2",
        modalidad: "Virtual",
        paciente: "",
        especialidad: "Cardiologia",
        localidad: "Escobar",
        telefono: "15122331",
        start: new Date(2022, 9, 7),
        end: new Date(2022, 9, 10),
        isBooked: false
    },
    {
        id: "3",
        modalidad: "Presencial",
        paciente: "",
        especialidad: "Clinica",
        localidad: "Escobar",
        telefono: "15884333",
        start: new Date(2022, 9, 20),
        end: new Date(2022, 9, 23),
        isBooked: false
    },
];

export const Oferta = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ title: "", start: "", end: "" });
    const [allEvents, setAllEvents] = useState(events);
    const [selected, setSelected] = useState();

    const handleAddEvent = () => {
        setAllEvents([...allEvents, newEvent]);
    }

    const handleSelected = (event) => {
        setSelected(event);
        Swal.fire(`Modalidad: ${event.modalidad},\n
        Telefono: ${event.telefono}, \n
        Estado: ${event.isBooked ? "oferta reservada": "oferta disponible"},\n
        Paciente: ${event.paciente ? event.paciente : " --"}`)
    };

    return (
        <div className="myCustomHeight">
            <h1 className="text-center">Calendario</h1>
            <form>
                <div className="row justify-content-center" style={{ position: "relative", zIndex: "999" }}>
                    <div className="col-auto">
                        <input className="form-control rounded-2" required type="text" placeholder="Telefono" value={newEvent.telefono} onChange={(e) => setNewEvent({ ...newEvent, telefono: e.target.value })} />
                    </div>
                    <div className="col-auto">
                        <input className="form-control rounded-2"  type="text" placeholder="Localidad" value={newEvent.localidad} onChange={(e) => setNewEvent({ ...newEvent, localidad: e.target.value })} />
                    </div>
                    <div className="col-auto">
                        <select className="form-select" value={newEvent.modalidad} name="modalidad" onChange={(e) => setNewEvent({ ...newEvent, modalidad: e.target.value })}>
                            <option value="Virtual">Virtual</option>
                            <option value="Presencial">Presencial</option>
                        </select>
                    </div>
                    <div className="col-auto">
                        <DatePicker className="form-control rounded-2" required placeholderText="Start Date" dateFormat="Pp" showTimeSelect selected={newEvent.start} onChange={(start) => setNewEvent({ ...newEvent, start })} />

                    </div>
                    <div className="col-auto">
                        <DatePicker className="form-control rounded-2" required tabindex="10" placeholderText="End Date" dateFormat="Pp" showTimeSelect selected={newEvent.end} onChange={(end) => setNewEvent({ ...newEvent, end })} />

                    </div>
                    <div className="col-auto">
                        <button className="btn btn-success" onClick={handleAddEvent}>
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
                    const backgroundColor = event.isBooked ? '#66bf4f' : '#ec6434';
                    return { style: { backgroundColor } }
                }}
            />
        </div>
    )
}
