package com.springbackend.cbs.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "chefs")
public class Chef {
	 

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(name = "name", nullable = false)
	    private String name;

	    @Column(name = "email", nullable = false, unique = true)
	    private String email;

	    @Column(name = "password", nullable = false)
	    private String password;
	    
	    private String speciality;
	    
	    private int experience;

	    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private List<Menu> menus;

	    @OneToMany(mappedBy = "chef", cascade = CascadeType.ALL)
	    @JsonManagedReference
	    private List<Booking> bookings; 
	    
	    
		public Chef() {
			super();
		}

		public Chef(Long id, String name, String email, String password, String speciality, int experience) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.password = password;
			this.speciality = speciality;
			this.experience = experience;
		}

		@Override
		public String toString() {
			return "Chef [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", speciality="
					+ speciality + ", experience=" + experience + "]";
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}
		public List<Menu> getMenus() {
			return menus;
		}

		public void setMenus(List<Menu> menus) {
			this.menus = menus;
		}

		public List<Booking> getBookings() {
			return bookings;
		}

		public void setBookings(List<Booking> bookings) {
			this.bookings = bookings;
		}
		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getSpeciality() {
			return speciality;
		}

		public void setSpeciality(String speciality) {
			this.speciality = speciality;
		}

		public int getExperience() {
			return experience;
		}

		public void setExperience(int experience) {
			this.experience = experience;
		}
	    
	    
}
