import React, { useState } from 'react';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
import './Login.css';

export default function Login() {
  const [formData, setFormData] = useState({
    email: '',
    password: ''
  });

  const [role, setRole] = useState('customer'); 
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');
  const navigate = useNavigate();

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleRoleChange = (e) => {
    setRole(e.target.value);
  };

  const handleSubmit = async (e) => {
  e.preventDefault();
  setError('');
  setSuccess('');

  try {
    const url =
      role === 'admin'
        ? 'http://localhost:8080/admin/login'  // Admin login API
        : 'http://localhost:8080/api/users/login';

    const res = await axios.post(url, formData);
    const user = res.data;

    if (role === 'admin') {
      // If response exists, admin login is valid
      setSuccess('Admin login successful!');
      localStorage.setItem('user', JSON.stringify({ ...user, role }));
      navigate('/Adminedit'); // Redirect to Admin Portal
    } else {
      setSuccess('Customer login successful!');
      localStorage.setItem('user', JSON.stringify({ ...user, role }));
      navigate('/Home'); // Customer redirect
    }
  } catch (err) {
    setError('Login failed: ' + (err.response?.data?.message || 'Invalid credentials'));
  }
};


  return (
    <div className="login-container">
      <h2>Login</h2>

      <form onSubmit={handleSubmit} className="login-form">
        <label>Email:</label>
        <input
          type="email"
          name="email"
          placeholder="Enter email"
          value={formData.email}
          onChange={handleChange}
          required
        />

        <label>Password:</label>
        <input
          type="password"
          name="password"
          placeholder="Enter password"
          value={formData.password}
          onChange={handleChange}
          required
        />

        <label>Role:</label>
        <select value={role} onChange={handleRoleChange}>
          <option value="customer">Customer</option>
          <option value="admin">Admin</option>
        </select>

        <button type="submit">Login</button>
      </form>

      {error && <p className="error">{error}</p>}
      {success && <p className="success">{success}</p>}
    </div>
  );
}
