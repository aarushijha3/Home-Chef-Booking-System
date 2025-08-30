import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Login from './pages/Login';
import Register from './pages/Register';
import Home from './pages/Home';
import Navbar from './components/Navbar';
import Chefs from './pages/Chefs';
import Menu from './pages/Menu';

import Booking from './pages/Booking';
import Billing from './pages/Billing';
import Aboutus from './pages/Aboutus';
import Adminedit from './pages/Adminedit';
function App() {
  return (
    <Router>
      <Navbar />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/chefs" element={<Chefs />} />
        <Route path="/menu" element={<Menu />} />
        <Route path="/about" element={<Aboutus/>} />
        <Route path="/bookings" element={<Booking />} />
        <Route path="/billing" element={<Billing />} />
         <Route path="/Adminedit" element={<Adminedit />} />
      </Routes>
    </Router>
  );
}

export default App;
