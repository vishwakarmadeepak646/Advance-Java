package com.Module.EventBooking;

import java.util.Date;

public class EventBookingBean {

	private long id;
	private String name;
	private Date date;
	private int seat;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getSeat() {
		return seat;
	}
	public void setSeat(Integer seat) {
		this.seat = seat;
	}

	
}
