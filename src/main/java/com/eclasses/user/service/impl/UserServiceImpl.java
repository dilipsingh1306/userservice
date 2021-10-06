package com.eclasses.user.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.eclasses.user.data.entity.UserRegisterEntity;
import com.eclasses.user.data.repository.UserRepoistory;
import com.eclasses.user.dto.UserDetailsDTO;
import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;
import com.eclasses.user.service.UserService;
import com.eclasses.user.util.UserAppConstants;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	UserRepoistory repository = null;
	BCryptPasswordEncoder encoder = null;

	@Autowired
	public UserServiceImpl(UserRepoistory repository, BCryptPasswordEncoder encoder) {
		this.repository = repository;
		this.encoder = encoder;

	}

	@Override
	public UserRegisterDTO registerUser(UserRegisterDTO dto) {

		log.info("Registering User");

		// Password Encryption
		dto.setPassword(encoder.encode(dto.getPassword()));

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserRegisterEntity entity = mapper.map(dto, UserRegisterEntity.class);

		log.info("Email Id  : " + entity.getEmailId());
		log.info("First Name :  " + entity.getFirstName());
		log.info("Last Name : " + entity.getLastName());
		log.info("Mobile Number  Id  : " + entity.getMobileNumber());
		log.info("Password : " + entity.getPassword());

		repository.save(entity);

		return dto;

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
	public UserDetailsDTO getUserDetailsByEmailId(String emailId) {

		UserDetailsDTO userDetails = null;

		Optional<UserRegisterEntity> record = repository.findById(emailId);

		if (record.isPresent()) {
			userDetails = new UserDetailsDTO();
			userDetails.setFirstName(record.get().getFirstName());
			userDetails.setLastName(record.get().getLastName());
			userDetails.setMobileNumber(record.get().getMobileNumber());
			userDetails.setEmailId(record.get().getEmailId());
		} else {
			log.info("User Not Found with email Id : " + emailId);
		}

		return userDetails;
	}

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

		UserRegisterEntity entity = repository.findByEmailId(emailId);
		if (entity == null) {
			throw new UsernameNotFoundException(emailId);
		}

		return new User(entity.getEmailId(), entity.getPassword(), true, true, true, true, new ArrayList<>());
	}
	
	
	
}
