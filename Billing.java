
package com.springbackend.cbs.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

 
@Entity
@Table(name = "billing")
public class Billing{
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    
	    private String userid;

	   
	    private double amount;

	  private String status;

	  @OneToOne
	    @JoinColumn(name = "booking_id")
	    private Booking booking;
	  
	  public Booking getBooking() {
		return booking;
	}

	  public void setBooking(Booking booking) {
		  this.booking = booking;
	  }

	  public Long getId() {
		  return id;
	  }

	  public void setId(Long id) {
		  this.id = id;
	  }

	  public String getUserid() {
		  return userid;
	  }

	  public void setUserid(String userid) {
		  this.userid = userid;
	  }

	  public double getAmount() {
		  return amount;
	  }

	  public void setAmount(double amount) {
		  this.amount = amount;
	  }

	  public String getStatus() {
		  return status;
	  }

	  public void setStatus(String status) {
		  this.status = status;
	  }

	  @Override
	  public String toString() {
		return "Billing [id=" + id + ", userid=" + userid + ", amount=" + amount + ", status=" + status + "]";
	  }

	  public Billing(Long id, String userid, double amount, String status) {
		super();
		this.id = id;
		this.userid = userid;
		this.amount = amount;
		this.status = status;
	  }

	  public Billing() {
		super();
	  }
	    
	
}    