package com.eclasses.user.service;

import org.springframework.stereotype.Service;

import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;
import com.eclasses.user.model.UserDetailsModel;

@Service
public interface UserService {
	
	
	public String registerUser(UserRegisterDTO request);

	public String updateUser(UserUpdateDTO updateDto);
	
	
	public UserDetailsModel getUserDetails(String emailId);
	

}
