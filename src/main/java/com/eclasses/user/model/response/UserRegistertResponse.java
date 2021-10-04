package com.eclasses.user.model.response;

public class UserRegistertResponse {

	private String mobileNumber;
	private String firstName;
	private String lastName;

	public UserRegistertResponse() {
		super();
	}

	public UserRegistertResponse(String mobileNumber, String firstName, String lastName) {
		super();
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
