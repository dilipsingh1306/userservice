package com.eclasses.user.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;
import com.eclasses.user.entity.UserRegisterEntity;
import com.eclasses.user.repository.UserRepoistory;
import com.eclasses.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepoistory repository;

	@Override
	public String registerUser(UserRegisterDTO registerDto) {

		log.info("Registering User");

		UserRegisterEntity entity = new UserRegisterEntity();

		entity.setEmailId(registerDto.getEmailId());
		entity.setFirstName(registerDto.getFirstName());
		entity.setLastName(registerDto.getLastName());
		entity.setMobileNumber(registerDto.getMobileNumber());
		entity.setPassword(registerDto.getPassword());

		Object value = repository.save(entity);

		System.out.println(value.getClass());

		return "User Registered";

	}

	@Override
	public String updateUser(UserUpdateDTO updateDto) {
		// TODO Auto-generated method stub
		log.info("Updating User");

		return "User Updated";

	}

}
