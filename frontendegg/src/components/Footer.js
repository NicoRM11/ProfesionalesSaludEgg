import React from 'react'
import { Col, Row } from 'react-bootstrap';
import logo from '../images/logo.png';

const Footer = () => {
  return (
    <footer className="container-xxl">
      <Row className="flex-row-reverse">
        <Col>
        <a className="h1 text-white" href="#">
          <img
            src={logo}
            width="150" height="50" />
        </a>
        </Col>
        
      </Row>
    </footer>
  )
}

export default Footer
