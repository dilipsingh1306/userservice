package com.eclasses.user.request;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserRegisterRequest {

	@NotNull(message="Email can't be empty.")
	@Email
	private String emailId;
	
	@NotEmpty(message="Mobile Number can't be empty.")
	//@NotNull(message="Mobile Number can't be empty.")
	private String mobileNumber;
	
	@NotNull(message="First Name can't be empty.")
	private String firstName;
	
	@NotNull(message="Last Name can't be empty.")
	private String lastName;
	
	@NotNull(message="Password can't be empty.")
	@Size(min=8, max=16, message="Password size should not be smaller than 8 and greater than 16 characters")
	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
