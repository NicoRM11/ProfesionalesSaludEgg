import React from 'react'

export const Navbar = () => {
  return (
    <nav className="navbar fixed-top navbar-expand-sm" >
      <div className="container-xxl">
        <a className="navbar-brand mb-2 h1 text-white" href="#">
          <img
            className="d-inline-block align-top"
            src="https://getbootstrap.com/docs/5.2/assets/brand/bootstrap-logo.svg"
            alt="Bootstrap" width="30" height="24"/>
            PDLS
        </a>
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
            <li className="nav-item active">
              <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="white" className="bi bi-person-circle" viewBox="0 0 16 16">
                <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0z" />
                <path fillRule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8zm8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1z" />
              </svg>
              <span className="text-white">  Iniciar Sesion</span>
            </li>
            


          </ul>
        </div>
      </div>

    </nav>
  )
}
