import React, { useState, useEffect } from "react";
import axios from "axios";
import "./Billing.css";

const Billing = () => {
  const [billings, setBillings] = useState([]);
  const [totalRevenue, setTotalRevenue] = useState(0);
  const [upiId, setUpiId] = useState("");

  useEffect(() => {
    const fetchBillingData = async () => {
      const storedUser = JSON.parse(localStorage.getItem("user"));
      if (!storedUser || !storedUser.id) {
        alert("Please log in to view billing.");
        return;
      }

      try {
        const res = await axios.get("http://localhost:8080/api/billings");
        const validBills = res.data.filter(
          (b) => b.bookingId !== null && b.userid === String(storedUser.id)
        );

        const detailedBills = await Promise.all(
          validBills.map(async (bill) => {
            const bookingRes = await axios.get(
              `http://localhost:8080/api/bookings/${bill.bookingId}`
            );
            const booking = bookingRes.data;

            const [userRes, chefRes, menuRes] = await Promise.all([
              axios.get(`http://localhost:8080/api/users/${booking.userId}`),
              axios.get(`http://localhost:8080/api/chefs/${booking.chefId}`),
              axios.get(`http://localhost:8080/api/menus/${booking.menuId}`),
            ]);

            return {
              ...bill,
              user: userRes.data,
              chef: chefRes.data,
              menu: menuRes.data,
            };
          })
        );

        setBillings(detailedBills);
        const revenue = detailedBills.reduce((sum, b) => sum + b.amount, 0);
        setTotalRevenue(revenue);
      } catch (error) {
        console.error("Error fetching billing data:", error);
      }
    };

    fetchBillingData();
  }, []);

  const handlePayment = () => {
    if (!upiId.trim()) {
      alert("Please enter your UPI ID to proceed.");
    } else {
      alert(`Payment request sent to UPI ID: ${upiId}`);
    }
  };

  return (
    <div className="billing-container">
      <h2>Your Billing History</h2>
      {billings.length === 0 ? (
        <p>No billing records found.</p>
      ) : (
        billings.map((b, index) => (
          <div key={index} className="billing-card">
            <h3>
              Bill #{b.id} - ₹{b.amount} ({b.status})
            </h3>

            <div>
              <h4>Chef</h4>
              <p>
                <strong>Name:</strong> {b.chef.name}
              </p>
              <p>
                <strong>Speciality:</strong> {b.chef.speciality}
              </p>
            </div>

            <div>
              <h4>Menu</h4>
              <p>
                <strong>Name:</strong> {b.menu.name}
              </p>
              <p>
                <strong>Price:</strong> ₹{b.menu.price}
              </p>
              {b.menu.imageUrl && (
                <img
                  src={b.menu.imageUrl}
                  alt={b.menu.name}
                  style={{ width: "200px" }}
                />
              )}
            </div>
          </div>
        ))
      )}

      <hr />
      <h3>Total Spent: ₹{totalRevenue}</h3>

      <div className="payment-section">
        <h4>Enter UPI ID for Payment</h4>
        <input
          type="text"
          value={upiId}
          onChange={(e) => setUpiId(e.target.value)}
          placeholder="example@upi"
          style={{ padding: "8px", width: "250px", marginRight: "10px" }}
        />
        <button onClick={handlePayment} style={{ padding: "8px 16px" }}>
          Pay Now
        </button>
      </div>
    </div>
  );
};

export default Billing;
