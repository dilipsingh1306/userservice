package com.eclasses.user.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.eclasses.user.dto.UserDetailsDTO;
import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;

public interface UserService extends UserDetailsService{

	public UserRegisterDTO registerUser(UserRegisterDTO request);

	public String updateUser(UserUpdateDTO updateDto);

	public UserDetailsDTO getUserDetailsByEmailId(String emailId);

}
