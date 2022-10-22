import React, { useState } from 'react';
import { Calendar, momentLocalizer } from 'react-big-calendar'
import moment from 'moment';
import DatePicker from "react-datepicker";
require('moment/locale/es.js');

const events = [
    {
        title: "Big Meeting",
        allDay: true,
        start: new Date(2022, 10, 1),
        end: new Date(2022, 10, 1),
    },
    {
        title: "Vacation",
        start: new Date(2022, 9, 7),
        end: new Date(2022, 9, 10),
    },
    {
        title: "Conference",
        start: new Date(2022, 9, 20),
        end: new Date(2022, 9, 23),
    },
];

export const Oferta = () => {
    const localizer = momentLocalizer(moment);
    const [newEvent, setNewEvent] = useState({ title: "", start: "", end: "" });
    const [allEvents, setAllEvents] = useState(events);
    console.log(newEvent)
    const handleAddEvent = () => {
        setAllEvents([...allEvents, newEvent]);
    }
    return (
        <div className="myCustomHeight">
            <h1>Calendar</h1>
            <h2>Add New Event</h2>
            <div className="row justify-content-center p-5" style={{ position:"relative", zIndex:"999" }}>
                <div className="col-auto">
                    <input type="text" placeholder="Add Title" value={newEvent.title} onChange={(e) => setNewEvent({ ...newEvent, title: e.target.value })} />

                </div>
                <div className="col-auto">
                    <DatePicker placeholderText="Start Date" dateFormat="Pp" showTimeSelect selected={newEvent.start} onChange={(start) => setNewEvent({ ...newEvent, start })} />

                </div>
                <div className="col-auto">
                    <DatePicker tabindex="10" placeholderText="End Date" dateFormat="Pp" showTimeSelect selected={newEvent.end} onChange={(end) => setNewEvent({ ...newEvent, end })} />

                </div>
                <div className="col-auto">
                    <button stlye={{ marginTop: "10px" }} onClick={handleAddEvent}>
                        Add Event
                    </button>
                </div>

            </div>
            <Calendar
                localizer={localizer}
                events={allEvents}
                startAccessor="start"
                endAccessor="end"
                style={{ height: 500, margin: "50px" }}
                messages={{
                    next: "sig",
                    previous: "ant",
                    today: "Hoy",
                    month: "Mes",
                    week: "Semana",
                    day: "Día"
                }}
            />
        </div>
    )
}
