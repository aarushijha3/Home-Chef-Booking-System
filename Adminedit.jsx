import React, { useEffect, useState } from "react";
import axios from "axios";
import "./Menuedit.css";

export default function MenuEdit() {
  const [chefs, setChefs] = useState([]);
  const [formData, setFormData] = useState({
    id: "",
    name: "",
    email: "",
    password: "",
    speciality: "",
    experience: ""
  });
  const [editing, setEditing] = useState(false);

 
  useEffect(() => {
    fetchChefs();
  }, []);

  const fetchChefs = async () => {
    try {
      const res = await axios.get("http://localhost:8080/admin/chefs");
      setChefs(res.data);
    } catch (err) {
      console.error(err);
      alert("Failed to fetch chefs");
    }
  };

  const handleChange = (e) => {
    setFormData({ ...formData, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (editing) {
     
        await axios.put(
          `http://localhost:8080/admin/chefs/${formData.id}`,
          formData
        );
        alert("Chef updated successfully!");
      } else {
       
        await axios.post("http://localhost:8080/api/admin/chefs", formData);
        alert("Chef added successfully!");
      }
      setFormData({ id: "", name: "", email: "", password: "", speciality: "", experience: "" });
      setEditing(false);
      fetchChefs(); 
    } catch (err) {
      console.error(err);
      alert("Failed to save chef");
    }
  };

  const handleEdit = (chef) => {
    setFormData(chef);
    setEditing(true);
  };

  const handleDelete = async (id) => {
    if (!window.confirm("Are you sure you want to delete this chef?")) return;
    try {
      await axios.delete(`http://localhost:8080/api/admin/chefs/${id}`);
      alert("Chef deleted successfully!");
      fetchChefs();
    } catch (err) {
      console.error(err);
      alert("Failed to delete chef");
    }
  };

  return (
    <div className="menu-edit-container">
      <h2>Admin - Manage Chefs</h2>

      <form onSubmit={handleSubmit} className="chef-form">
        <input
          type="text"
          name="name"
          placeholder="Name"
          value={formData.name}
          onChange={handleChange}
          required
        />
        <input
          type="email"
          name="email"
          placeholder="Email"
          value={formData.email}
          onChange={handleChange}
          required
        />
        <input
          type="password"
          name="password"
          placeholder="Password"
          value={formData.password}
          onChange={handleChange}
          required
        />
        <input
          type="text"
          name="speciality"
          placeholder="Speciality"
          value={formData.speciality}
          onChange={handleChange}
          required
        />
        <input
          type="number"
          name="experience"
          placeholder="Experience (years)"
          value={formData.experience}
          onChange={handleChange}
          required
        />
        <button type="submit">{editing ? "Update Chef" : "Add Chef"}</button>
      </form>

      <h3>Existing Chefs</h3>
      <table className="chef-table">
        <thead>
          <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Speciality</th>
            <th>Experience</th>
            <th>Actions</th>
          </tr>
        </thead>
        <tbody>
          {chefs.map((chef) => (
            <tr key={chef.id}>
              <td>{chef.id}</td>
              <td>{chef.name}</td>
              <td>{chef.email}</td>
              <td>{chef.speciality}</td>
              <td>{chef.experience}</td>
              <td>
                <button onClick={() => handleEdit(chef)}>Edit</button>
                <button onClick={() => handleDelete(chef.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
