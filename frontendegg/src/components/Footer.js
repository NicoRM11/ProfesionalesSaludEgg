import React from 'react'
import logo from '../images/logo.png';


const Footer = () => {
  return (
    <footer className=" mt-5 py-3">
      <div className="container">
        <p className="float-end mb-1 border-left align-self-center "  >
          <a className="h1 text-white" href="#">
            <img
              src={logo}
              width="150" height="50" />
          </a>
        </p>
        


        <div className='desarrolladores d-flex justify-content-around'>


          <div className='backEnd text-white d-flex '>



            <div className='roles m-3'>
              <h6>Desarrolladores BackEnd</h6>
            </div>

            <div className='integrantes'>
              <h6>Nicolas Romano</h6>
              <h6>Walter Tapia</h6>
            </div>


            <div className='integrantes'>
              <h6>Santiago Niveyro</h6>
              <h6>Emiliano Donoso</h6>


            </div>


          </div>



          <div className='frontEnd text-white d-flex  '>

            <div className='roles m-3'>
              <h6>Desarrolladores FrontEnd</h6>
            </div>

            <div className='integrantes'>
              <h6>Gabriel Hernan Figueroa</h6>
              <h6>Cecilia Longhi</h6>
            </div>




          </div>
        </div>



      </div>















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

    </footer >
  )
}

export default Footer
