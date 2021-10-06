package com.eclasses.user.dto;

public class UserDetailsDTO {

	private String mobileNumber;
	private String firstName;
	private String lastName;
	private String emailId;

	public UserDetailsDTO() {
		super();
	}

	public UserDetailsDTO(String mobileNumber, String firstName, String lastName, String emailId) {
		super();
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}
