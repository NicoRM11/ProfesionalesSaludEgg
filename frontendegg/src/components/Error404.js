import React, { useState } from 'react'
import { Link } from 'react-router-dom'
import logo from '../images/logo.png';


export const Error404 = () => {
    const rol = JSON.parse(localStorage.getItem('rol'))

    return (
        <>
            <nav className="navbar  navbar-expand-sm" >
                <div className="container-xxl">
                    <div className="navbar-brand mb-0 h1 text-white" href="#">
                        {rol==="ROLE_PROFESIONAL" &&
                        <Link to="/inicio/Profesional"> <img src={logo} width="150" height="50" /> </Link> }
                        {rol==="ROLE_ADMIN" &&
                        <Link to="/listar/usuarios"> <img src={logo} width="150" height="50" /> </Link> }
                        {rol==="ROLE_GUEST" &&
                        <Link to="/inicio"> <img src={logo} width="150" height="50" /> </Link>  }
                        
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

                </div>
            </nav>

            <section className="container ">
                <div className="row justify-content-center align-items-center mt-5 mainContenedor">
                    <div className="col-6 text-center text-white bg-danger rounded-4">
                        <h1 className="mt-5">Eror 404</h1>
                        <h1 className="py-5">Not found</h1>
                    </div>
                </div>
            </section>
        </>
    )
}
