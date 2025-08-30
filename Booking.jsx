import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Booking.css";

const Booking = () => {
  const [bookings, setBookings] = useState([]);

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem("user"));
    if (!user || !user.id) return;

    axios.get(`http://localhost:8080/api/bookings/user/${user.id}`)
      .then(async (res) => {
        const bookingsWithDetails = await Promise.all(
          res.data.map(async (booking) => {
            try {
              const [userRes, chefRes, menuRes] = await Promise.all([
                axios.get(`http://localhost:8080/api/users/${booking.userId}`),
                axios.get(`http://localhost:8080/api/chefs/${booking.chefId}`),
                axios.get(`http://localhost:8080/api/menus/${booking.menuId}`),
              ]);

              return {
                ...booking,
                user: userRes.data,
                chef: chefRes.data,
                menu: menuRes.data,
              };
            } catch (err) {
              console.error("Error fetching details:", err);
              return null;
            }
          })
        );

        const validBookings = bookingsWithDetails.filter(Boolean);
        setBookings(validBookings);
      })
      .catch(console.error);
  }, []);

  const handleDelete = async (id) => {
    const confirmed = window.confirm("Are you sure you want to delete this booking?");
    if (!confirmed) return;

    try {
      await axios.delete(`http://localhost:8080/api/bookings/${id}`);
      setBookings((prev) => prev.filter((b) => b.id !== id));
    } catch (err) {
      console.error("Error deleting booking:", err);
      alert("Failed to delete booking.");
    }
  };

  return (
    <div className="booking-container">
      <h2>Your Bookings</h2>
      {bookings.length === 0 ? (
        <p>No bookings found.</p>
      ) : (
        bookings.map((b) => (
          <div className="booking-card" key={b.id}>
            <h3>Booking #{b.id}</h3>
            <p><strong>Date:</strong> {b.date}</p>

            <div className="card-section">
              <h4>User Info</h4>
              <p><strong>Name:</strong> {b.user?.name}</p>
              <p><strong>Email:</strong> {b.user?.email}</p>
            </div>

            <div className="card-section">
              <h4>Chef Info</h4>
              <p><strong>Name:</strong> {b.chef?.name}</p>
              <p><strong>Specialty:</strong> {b.chef?.speciality}</p>
            </div>

            <div className="card-section">
              <h4>Menu Info</h4>
              <img
                src={b.menu?.imageUrl}
                alt={b.menu?.name}
                style={{ maxWidth: "200px" }}
              />
              <p><strong>Dish:</strong> {b.menu?.name}</p>
              <p><strong>Price:</strong> ₹{b.menu?.price}</p>
            </div>

            <button className="delete-button" onClick={() => handleDelete(b.id)}>
              Delete Booking
            </button>
          </div>
        ))
      )}
    </div>
  );
};

export default Booking;
