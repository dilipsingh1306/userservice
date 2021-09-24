package com.eclasses.user.service;

import org.springframework.stereotype.Service;

import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;

@Service
public interface UserService {
	
	
	public String registerUser(UserRegisterDTO request);

	public String updateUser(UserUpdateDTO updateDto);
	
	

}
