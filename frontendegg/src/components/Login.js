import axios from 'axios';
import React, { useState } from 'react'
import { Link, useNavigate } from 'react-router-dom';
import Swal from 'sweetalert2';
import { NavSesionGuest } from './NavSesionGuest';
import logo from '../images/logo.png';


const Login = () => {
  const [usuario, setUsuario] = useState("");
  const [password, setPassword] = useState("");
  const [data, setdata] = useState({});

  let navigate = useNavigate()

  const URL = "http://localhost:8080/login";
  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const response = await axios.post(URL, JSON.stringify({ usuario, password }),
        {
          headers: { 'Content-Type': 'application/json' }
        }
      );
      const rol = response.data.authorities[0].authority;
      localStorage.setItem('rol', JSON.stringify(rol));
      localStorage.setItem('usuario', JSON.stringify(usuario));
      localStorage.setItem('password', JSON.stringify(password));
      if(rol==='ROLE_GUEST'){
        navigate('/inicio');
      }
      if(rol==='ROLE_PROFESIONAL'){
        navigate('/inicio/Profesional');
      }
      if(rol==='ROLE_ADMIN'){
        navigate('/listar/usuarios');
      }
      

    } catch (error) {
      console.log(error)
      if (error.response.status === 406 ||error.response.status === 404) {
        Swal.fire({
          icon: 'error',
          title: 'Oops...',
          text: `No se pudo iniciar sesion, ${error.response.data.messages[0]} !`,
        })
      }
      console.log(error)
    }
  }
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
    
    <section className="container ">
      <div className="row justify-content-center align-items-center mt-5 mainContenedor">
        <form className="Formulario col-md-4 col-xxl-5 py-4 rounded-4 text-white" onSubmit={handleSubmit}>
          <div className="row justify-content-center">
            <div className="col-10 mb-4 text-center">
              <label className="mb-3 h5">Ingresá tu email</label>
              <input className="form-control rounded-4" type="text" value={usuario} onChange={e => setUsuario(e.target.value)} required />
            </div>
          </div>
          <div className="row justify-content-center ">
            <div className="col-10 mb-4 text-center">
              <label className="mb-3 h5">Contraseña</label>
              <input className="form-control rounded-4" type="password" value={password} onChange={e => setPassword(e.target.value)} required />
            </div>
          </div>
          <div className="row justify-content-center ">
            <button className="cta col-6" type="submit">
              <span>Iniciar</span>
              <svg viewBox="0 0 13 10" height="10px" width="15px">
                <path d="M1,5 L11,5"></path>
                <polyline points="8 1 12 5 8 9"></polyline>
              </svg>
            </button>
          </div>
        </form>
        <div className="row text-center " >
          <label className="textoVerde h6">¿No tenes cuenta? <Link to="/register" className="text-decoration-none">Registrate</Link> </label>
        </div>
      </div>
    </section>
    </>
  )
}

export default Login
