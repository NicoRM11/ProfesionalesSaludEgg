
import './App.css';
import { Main } from './components/Main';
import { Navbar } from './components/NavBar';
import Footer from './components/Footer';
import Login from './components/Login';
import { Route, Routes } from 'react-router-dom';
import { GuestProfile } from './components/GuestProfile';

function App() {
  return (
    <div className="">
      <Navbar></Navbar>    
      <Routes>
        <Route exact path="/register" element={<Main/>} />
        <Route exact path="/login" element={<Login/>} />
        <Route exact path="/GuestProfile" element={<GuestProfile/>} />

      </Routes>
        
      <Footer></Footer>
    </div>
  );
}

export default App;
