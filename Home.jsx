import React from 'react';
import './Home.css';
import { Link } from 'react-router-dom';

const Home = () => {
  return (
    <div className="home-container">
      <div className="hero-section">
        <div className="hero-text">
          <h1>Welcome to HomeChef</h1>
          <p>Your gateway to delicious, home-cooked meals delivered right to your door.</p>
          <Link to="/menu" className="hero-button">Explore Menus</Link>
        </div>
        <div className="hero-image">
          <img src="/images/cheficon.jpg" alt="Delicious food" />
        </div>
      </div>

      <div className="features-section">
        <h2>Why Choose HomeChef?</h2>
        <div className="features">
          <div className="feature-card">
            <img src="/images/chefim.jpg" alt="Chef" />
            <h3>Certified Chefs</h3>
            <p>We partner with experienced and verified home chefs.</p>
          </div>
          <div className="feature-card">
            <img src="/images/menu.avif" alt="Meals" />
            <h3>Diverse Menus</h3>
            <p>Choose from a wide range of cuisines and dishes.</p>
          </div>
          <div className="feature-card">
            <img src="/images/delivery.jpg" alt="Delivery" />
            <h3>Timely Delivery</h3>
            <p>Fresh meals delivered to your doorstep on time.</p>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
