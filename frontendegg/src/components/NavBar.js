import React from 'react';
import { Link, useLocation } from 'react-router-dom';
import logo from '../images/logo.png';
import { NavLogin } from './NavLogin';

export const Navbar = () => {

  const location = useLocation();

  return (

    <nav className="navbar  navbar-expand-sm" >
      <div className="container-xxl">
        <div className="navbar-brand mb-0 h1 text-white" href="#">
          <Link to="/inicio">
            <img
              src={logo}
              width="150" height="50" />
          </Link>
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
            <NavLogin></NavLogin>
          </ul>
        </div>
      </div>

    </nav>
  )
}
