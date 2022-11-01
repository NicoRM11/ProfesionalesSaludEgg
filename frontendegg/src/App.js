
import './App.css';
import { Main } from './components/Main';
import { Navbar } from './components/NavBar';
import Footer from './components/Footer';
import Login from './components/Login';
import { Route, Routes } from 'react-router-dom';
import { GuestProfile } from './components/GuestProfile';
import { ProfesionalProfile } from './components/ProfesionalProfile';
import Inicio from './components/Inicio';
import { Error404 } from './components/Error404';
import { OfertaProfesional } from './components/OfertaProfesional';
import { Cartilla } from './components/Cartilla';
import { InicioProfesional } from './components/InicioProfesional';
import { ListarAdmin } from './components/ListarAdmin';


import "react-datepicker/dist/react-datepicker.css";
import 'react-big-calendar/lib/css/react-big-calendar.css';
import { OfertaGuest } from './components/OfertaGuest';
import { MisTurnos } from './components/MisTurnos';
import { FichaGuest } from './components/FichaGuest';
import { FichaProfesional } from './components/FichaProfesional';
import { OfertasAdmin } from './components/OfertasAdmin';

function App() {
  const rol = JSON.parse(localStorage.getItem('rol'))
  return (
    <div className="">
        <Routes>
          <Route exact path="/inicio" forceRefresh={true} element={<Inicio />} />
          <Route exact path="/register" element={<Main />} />
          <Route exact path="/login" element={<Login />} />          
          <Route exact path="/inicio/Profesional" element={<InicioProfesional />} />
          <Route exact path="/ProfesionalProfile" element={<ProfesionalProfile />} />
          <Route exact path="/Oferta/profesional" element={<OfertaProfesional />} />
          <Route exact path="/Ficha/lista/paciente" element={<FichaProfesional />} />          
          <Route exact path="/GuestProfile" element={<GuestProfile />} />
          <Route exact path="/Oferta/guest" element={<OfertaGuest />} />
          <Route exact path="/misTurnos" element={<MisTurnos />} />
          <Route exact path="/cartilla" forceRefresh={true} element={<Cartilla />} />
          <Route exact path="/Ficha/lista" element={<FichaGuest />} />
          <Route exact path="/listar/usuarios" element={<ListarAdmin />} /> 
          <Route exact path="/ofertas/listado" element={<OfertasAdmin />} />    
          <Route path="*" element={<Error404 />} />       
        </Routes>
        <Footer></Footer>
    </div>
  );
}

export default App;
