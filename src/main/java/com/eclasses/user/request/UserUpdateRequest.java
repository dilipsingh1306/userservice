package com.eclasses.user.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserUpdateRequest {

	@NotEmpty(message = "Mobile Number can't be empty.")
	private String mobileNumber;

	@NotNull(message = "First Name can't be empty.")
	private String firstName;

	@NotNull(message = "Last Name can't be empty.")
	private String lastName;

	@NotNull(message = "Password can't be empty.")
	@Size(min = 8, max = 16, message = "Password size should not be smaller than 8 and greater than 16 characters")
	private String password;

	public UserUpdateRequest() {
		super();
	}

	public UserUpdateRequest(@NotEmpty(message = "Mobile Number can't be empty.") String mobileNumber, @NotNull(message = "First Name can't be empty.") String firstName,
			@NotNull(message = "Last Name can't be empty.") String lastName,
			@NotNull(message = "Password can't be empty.") @Size(min = 8, max = 16, message = "Password size should not be smaller than 8 and greater than 16 characters") String password) {
		super();
		this.mobileNumber = mobileNumber;
		this.firstName = firstName;
		this.lastName = lastName;
		this.password = password;
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
