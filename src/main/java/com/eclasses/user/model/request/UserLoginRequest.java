package com.eclasses.user.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserLoginRequest {

	@NotNull(message = "Email can't be empty.")
	@Email
	private String emailId;

	@NotNull(message = "Password can't be empty.")
	@Size(min = 8, max = 16, message = "Password size should not be smaller than 8 and greater than 16 characters")
	private String password;

	public UserLoginRequest() {
		super();
	}

	public UserLoginRequest(@NotNull(message = "Email can't be empty.") @Email String emailId,
			@NotEmpty(message = "Mobile Number can't be empty.") @NotNull(message = "Password can't be empty.") @Size(min = 8, max = 16, message = "Password size should not be smaller than 8 and greater than 16 characters") String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
