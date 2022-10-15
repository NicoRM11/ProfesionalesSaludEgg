import React from 'react'
import logo from '../images/logo.png';


const Footer = () => {
  return (
    <footer className=" mt-5 py-3">
      <div className="container">
        <p className="float-end mb-1 border-left" >
          <a className="h1 text-white" href="#">
            <img
              src={logo}
              width="150" height="50" />
          </a>
        </p>

        <p className="mb-1"><a href="/">Preguntas frecuentes</a></p>

        <p className="mb-0"> <a href="/">Sobre nosotros</a></p>





        {/* <Row className="r m-3 flex-row-reverse">
        <Col>
        <a className="h1 text-white" href="#">
          <img
            src={logo}
            width="150" height="50" />
        </a>
        </Col>

        <Col>
          <a className="h3 text-white " href="#"> Sobre nosotros</a>
        </Col>
        
        <Col>
          <a className='h3 text-white' href="#"> Preguntas frecuentes</a>
        </Col>

        <Col>
        
        </Col>
        
      </Row> */}
      </div>
    </footer>
  )
}

export default Footer
