import React from 'react'

const Inicio = () => {
    const usuario = JSON.parse(localStorage.getItem('usuario'))
    const password = JSON.parse(localStorage.getItem('password'))
    return (
        <div className="mainContenedor mt-4 text-center">
            <h1>INICIO</h1>    
        </div>
    )
}

export default Inicio
