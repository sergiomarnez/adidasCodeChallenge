package com.adidas.events.service.model;

import java.util.Date;

public class Event {

	private String id;
	private String description;
	private Date creationDate;
	
	public Event(String id, String description, Date creationDate) {
		super();
		this.id = id;
		this.description = description;
		this.creationDate = creationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
		
}
