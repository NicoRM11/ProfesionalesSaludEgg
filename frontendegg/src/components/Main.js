import React, { useState } from 'react'
import { ButtonGroup, ToggleButton } from 'react-bootstrap';
import { ProfesionalRegister } from './ProfesionalRegister';

import { GuestRegister} from './GuestRegister';

export const Main = () => {

  const [radioValue, setRadioValue] = useState('1');
  const radios = [
    { name: 'Registrarse como paciente', value: '1' },
    { name: 'Registrarse como profesional', value: '2' },
  ];
  return (
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
  )
}
