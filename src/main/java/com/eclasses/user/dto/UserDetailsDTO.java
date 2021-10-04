package com.eclasses.user.dto;

public class UserDetailsDTO {

	private String mobileNumber;
	private String firstName;
	private String lastName;

	public UserDetailsDTO() {
		super();
	}

	public UserDetailsDTO(String mobileNumber, String firstName, String lastName) {
		super();
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


}
