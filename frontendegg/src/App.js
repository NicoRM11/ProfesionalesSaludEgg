
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

function App() {
  return (
    <div className="">
        <Navbar></Navbar>
        <Routes>
          <Route exact path="/inicio" element={<Inicio />} />
          <Route exact path="/register" element={<Main />} />
          <Route exact path="/login" element={<Login />} />
          <Route exact path="/GuestProfile" element={<GuestProfile />} />
          <Route exact path="/ProfesionalProfile" element={<ProfesionalProfile />} />
          <Route exact path="/Oferta" element={<Oferta />} />
          <Route path="*" element={<Error404 />} />
        </Routes>
        <Footer></Footer>
    </div>
  );
}

export default App;
