import React from 'react'

const Login = () => {
  return (
    <section ClassName="container ">
      <div className="row justify-content-center align-items-center mt-5 mainContenedor">
        <form className="Formulario col-md-3 col-xxl-4 py-4 rounded-4 text-white">
          <div className="row justify-content-center">
            <div className="col-10 mb-4 text-center">
              <label className="mb-3 h5">Ingresá tu email</label>
              <input className="form-control rounded-4" type="text" />
            </div>
          </div>
          <div className="row justify-content-center ">
            <div className="col-10 mb-4 text-center">
              <label className="mb-3 h5">Contraseña</label>
              <input className="form-control rounded-4" type="password" />
            </div>
          </div>
          <div className="row justify-content-center ">
              <button className="cta col-6" type="submit">
                <span>Registrarse</span>
                <svg viewBox="0 0 13 10" height="10px" width="15px">
                  <path d="M1,5 L11,5"></path>
                  <polyline points="8 1 12 5 8 9"></polyline>
                </svg>
              </button>
          </div>
        </form>
        <div className="row text-center" >
          <label className="textoVerde h6">¿No tenes cuenta? Registrate</label>
        </div>
      </div>
    </section>
  )
}

export default Login
