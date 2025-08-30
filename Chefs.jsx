import React, { useEffect, useState } from 'react';
import axios from 'axios';
import "./Chef.css"; 
import chef1 from '../assets/chef1.jpg';
import chef2 from '../assets/chef2.jpg';
import chef3 from '../assets/chef3.jpg';
import chef4 from '../assets/chef4.jpg';
import chef5 from '../assets/chef5.jpg';
import chef7 from '../assets/chef7.jpg';
const chefImages = {
  1: chef4,
  2: chef2,
  3: chef1,
  4: chef5,
  5: chef3,
  6: chef7
};

const Chefs = () => {
  const [chefs, setChefs] = useState([]);

  useEffect(() => {
    axios.get("http://localhost:8080/api/chefs")
      .then((response) => {
        setChefs(response.data);
      })
      .catch((error) => {
        console.error("Error fetching chefs:", error);
      });
  }, []);

  return (
    <div className="chefs-container">
      <h2>Our Chefs</h2>
      <div className="chefs-grid">
        {chefs.map((chef) => (
          <div key={chef.id} className="chef-card">
            <img
              src={chefImages[chef.id] || chef1}
              alt={chef.name}
              className="chef-image"
            />
            <h3>{chef.name}</h3>
            <p><strong>Email:</strong> {chef.email}</p>
            <p><strong>Experience:</strong> {chef.experience} years</p>
            <p><strong>Speciality:</strong> {chef.speciality}</p>
            
          </div>
        ))}
      </div>
    </div>
  );
};

export default Chefs;
