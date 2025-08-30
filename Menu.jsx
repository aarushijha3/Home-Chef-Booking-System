import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Menu.css";
import { useNavigate } from "react-router-dom";

const Menus = () => {
  const [menus, setMenus] = useState([]);
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user"));
  const userId = user?.id;

  useEffect(() => {
    axios
      .get("http://localhost:8080/api/menus")
      .then((response) => {
        setMenus(response.data);
      })
      .catch((error) => {
        console.error("Error fetching menus:", error);
      });
  }, []);

  const handleBook = async (menu) => {
    if (!userId) {
      alert("Please log in to make a booking.");
      return;
    }

    if (!menu.id || !menu.chefId) {
      alert("Menu or chef information is incomplete. Cannot proceed with booking.");
      return;
    }

    const bookingData = {
      userId: parseInt(userId),
      chefId: menu.chefId,
      menuId: menu.id,
      date: new Date().toISOString().split("T")[0], 
    };

    try {
      
      const bookingRes = await axios.post("http://localhost:8080/api/bookings", bookingData);
      const booking = bookingRes.data;

      if (!booking.id) {
        throw new Error("Booking ID missing in response.");
      }

      
      const billingData = {
        userid: userId,
        amount: menu.price,
        status: "confirmed",
        bookingId: booking.id,
      };

      await axios.post("http://localhost:8080/api/billings", billingData);

      alert("Booking and billing successful!");
      navigate("/bookings");
    } catch (error) {
      console.error("Error during booking or billing:", error);
      alert("Booking failed. Please try again.");
    }
  };

  return (
    <div className="menu-container">
      <h2>Available Menus</h2>
      <div className="card-grid">
        {menus.map((menu) => (
          <div key={menu.id} className="menu-card">
            <img
              src={menu.imageUrl || "https://via.placeholder.com/300x200?text=No+Image"}
              alt={menu.name}
              style={{
                width: "100%",
                height: "200px",
                objectFit: "cover",
                borderRadius: "8px",
              }}
            />
            <h3>{menu.name}</h3>
            <p><strong>Price:</strong> ₹{menu.price}</p>
            <p><strong>Description:</strong> {menu.description}</p>
            <p><strong>Chef ID:</strong> {menu.chefId ?? "Not Assigned"}</p>
            <button onClick={() => handleBook(menu)}>Book Menu</button>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Menus;
