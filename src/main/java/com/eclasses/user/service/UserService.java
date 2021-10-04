package com.eclasses.user.service;

import com.eclasses.user.dto.UserDetailsDTO;
import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;

public interface UserService {

	public UserRegisterDTO registerUser(UserRegisterDTO request);

	public String updateUser(UserUpdateDTO updateDto);

	public UserDetailsDTO getUserDetails(String emailId);

}
