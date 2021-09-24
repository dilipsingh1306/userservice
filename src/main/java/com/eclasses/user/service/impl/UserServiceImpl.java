package com.eclasses.user.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;
import com.eclasses.user.entity.UserRegisterEntity;
import com.eclasses.user.model.UserDetailsModel;
import com.eclasses.user.repository.UserRepoistory;
import com.eclasses.user.service.UserService;
import com.eclasses.user.util.UserAppConstants;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepoistory repository;

	@Override
	public String registerUser(UserRegisterDTO dto) {

		log.info("Registering User");

		UserRegisterEntity entity = new UserRegisterEntity();

		entity.setEmailId(dto.getEmailId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setMobileNumber(dto.getMobileNumber());
		entity.setPassword(dto.getPassword());

		repository.save(entity);

		return UserAppConstants.USER_CREATED;

	}

	@Override
	public String updateUser(UserUpdateDTO dto) {
		log.info("Updating User");

		UserRegisterEntity entity = new UserRegisterEntity();
		entity.setEmailId(dto.getEmailId());
		entity.setFirstName(dto.getFirstName());
		entity.setLastName(dto.getLastName());
		entity.setMobileNumber(dto.getMobileNumber());
		entity.setPassword(dto.getPassword());

		repository.save(entity);

		return UserAppConstants.USER_UPDATED;

	}

	@Override
	public UserDetailsModel getUserDetails(String emailId) {

		UserDetailsModel userDetails = null;

		Optional<UserRegisterEntity> record = repository.findById(emailId);

		if (record.isPresent()) {
			userDetails = new UserDetailsModel();
			userDetails.setFirstName(record.get().getFirstName());
			userDetails.setLastName(record.get().getLastName());
			userDetails.setMobileNumber(record.get().getMobileNumber());
		} else {
			log.info("User Not Found with email Id : " + emailId);
		}

		return userDetails;
	}

}
