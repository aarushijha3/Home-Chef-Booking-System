import { Link, useNavigate } from 'react-router-dom';
import { useEffect, useState } from 'react';
import './Navbar.css';
export default function Navbar() {
  const [isLoggedIn, setIsLoggedIn] = useState(!!localStorage.getItem('user'));
  const navigate = useNavigate();

  useEffect(() => {
    const handleStorageChange = () => {
      setIsLoggedIn(!!localStorage.getItem('user'));
    };

   
    window.addEventListener('storage', handleStorageChange);

 
    return () => {
      window.removeEventListener('storage', handleStorageChange);
    };
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('user');
    setIsLoggedIn(false);
    navigate('/login');
  };

  return (
    <nav style={{ padding: '1rem', background: '#f0f0f0' }}>
      <Link to="/">Home</Link>{" | "}
      {!isLoggedIn && <Link to="/login">Login</Link>}{" "}
      {!isLoggedIn && <Link to="/register">Register</Link>}{" "}

      {isLoggedIn && (
        <>
          <Link to="/about">About Us</Link>{" | "}
          <Link to="/menu">Menu</Link>{" | "}
          <Link to="/chefs">Chefs</Link>{" | "}
          <Link to="/bookings">Booking</Link>{" | "}
          <Link to="/billing">Billing</Link>{" | "}
          <button onClick={handleLogout} style={{ marginLeft: '10px' }}>Logout</button>
        </>
      )}
    </nav>
  );
}
