package com.springbackend.cbs.dto;

public class BillingDTO {
	private Long id;

    
    private String userid;

   
    private double amount;

  private String status;
  private Long bookingId;
  public Long getId() {
	return id;
  }

  public Long getBookingId() {
	return bookingId;
}

  public void setBookingId(Long bookingId) {
	this.bookingId = bookingId;
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
  
  
}
