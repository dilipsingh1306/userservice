package com.eclasses.user.response;

public class UserRegistertResponse {

	private String emailId;
	private String mobileNumber;
	private String firstName;
	private String lastName;
	
	

	public UserRegistertResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRegistertResponse(String emailId, String mobileNumber, String firstName, String lastName) {
		super();
		this.emailId = emailId;
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

}
