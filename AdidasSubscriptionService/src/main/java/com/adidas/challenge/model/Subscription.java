package com.adidas.challenge.model;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Subscription {

	@Id
	public ObjectId id;
	@NotNull(message = "Email cannot be null")
	@Email(message = "Email should be valid")
	private String email;
	private String firstName;
	private String gender;
	@NotNull(message = "Date of birth cannot be null")
	private Date dateOfBirth;
	@NotNull(message = "Consent cannot be null")
	private Boolean consent;
	@NotNull(message = "Campaign ID cannot be null")
	private Integer newsletterId;


	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Subscription(ObjectId id, String email, String firstName, String gender, Date dateOfBirth, Boolean consent,
			Integer newsletterId) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.consent = consent;
		this.newsletterId = newsletterId;
	}

	// ObjectId needs to be converted to string
	public String getId() { 
		return id.toHexString(); 
	}
	
	public void setId(ObjectId id) { 
		this.id = id; 
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Boolean getConsent() {
		return consent;
	}

	public void setConsent(Boolean consent) {
		this.consent = consent;
	}

	public Integer getNewsletterId() {
		return newsletterId;
	}

	public void setNewsletterId(Integer newsletterId) {
		this.newsletterId = newsletterId;
	}



}
