import React, { useState } from 'react'
import { ButtonGroup, ToggleButton } from 'react-bootstrap';
import { ProfesionalRegister } from './ProfesionalRegister';
import logo from '../images/logo.png';
import { GuestRegister} from './GuestRegister';
import { NavSesionGuest } from './NavSesionGuest';
import { Link } from 'react-router-dom';

export const Main = () => {
  const [data, setdata] = useState({});
  const [radioValue, setRadioValue] = useState('1');
  const radios = [
    { name: 'Registrarse como paciente', value: '1' },
    { name: 'Registrarse como profesional', value: '2' },
  ];
  return (
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
    
    <main className="container text-center py-5">
      
      <ButtonGroup className="btn-select-reg">
        {radios.map((radio, idx) => (
          <ToggleButton
            key={idx}
            id={`radio-${idx}`}
            type="radio"
            variant={idx % 2 ? 'outline-success' : 'outline-success'}
            name="radio"
            value={radio.value}
            checked={radioValue === radio.value}
            onChange={(e) => setRadioValue(e.currentTarget.value)}
          >
            {radio.name}
          </ToggleButton>
        ))}
      </ButtonGroup>
      {radioValue==1 ? <GuestRegister ></GuestRegister>:<ProfesionalRegister></ProfesionalRegister>}

    </main>
    </>
  )
}
