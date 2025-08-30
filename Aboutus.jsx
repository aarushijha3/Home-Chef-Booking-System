import React from 'react';
import './AboutUs.css';

export default function AboutUs() {
  return (
    <div className="about-page">
      <div className="about-container">
        <h1>About Us</h1>
        <p>
          Welcome to HomeChef, where food meets love. Our platform connects home chefs with food lovers
          looking for authentic, homemade meals.
        </p>
        <img
           src="/images/chefim.jpg"
          alt="Cooking"
          className="about-image"
        />

        <div className="about-section">
          <h2>Our Mission</h2>
          <p>
            To empower talented home chefs and bring traditional flavors to modern homes. We strive to
            support local talent and promote healthy, home-cooked food culture.
          </p>
        </div>

        <div className="about-section">
          <h2>Why Choose Us?</h2>
          <ul>
            <li>Curated menus from passionate home chefs</li>
            <li>Fresh, homemade meals delivered to your doorstep</li>
            <li>Support for local culinary talent</li>
          </ul>
        </div>
      </div>
    </div>
  );
}
