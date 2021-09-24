package com.eclasses.user.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.request.UserRegisterRequest;
import com.eclasses.user.response.UserRegistertResponse;
import com.eclasses.user.service.UserService;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService service;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerUser(@Valid @RequestBody UserRegisterRequest request) {

		log.info("\nEmail Id : " + request.getEmailId() + " \nPassword : " + request.getPassword() + " \nMobile Numer : " + request.getMobileNumber() + " \nFirst Name : "
				+ request.getFirstName() + " \nLast Name : " + request.getLastName());

		System.out.println("User Registration Started ");
		
		UserRegisterDTO registerDto = new UserRegisterDTO();
		registerDto.setEmailId(request.getEmailId());
		registerDto.setFirstName(request.getFirstName());
		registerDto.setLastName(request.getLastName());
		registerDto.setMobileNumber(request.getMobileNumber());
		registerDto.setPassword(request.getPassword());
		
		String response = service.registerUser(registerDto);
		
		
		return response;
	}

	@PutMapping(path = "/update", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE })
	public String updateUser() {

		log.info("User Details Updated.");

		return "User Details Updated.";

	}

	@GetMapping(path = "/{userID}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRegistertResponse> getUserDetails(@PathVariable String userID) {
		
		
		log.info("Lookup User Id : "+userID);
		
		UserRegistertResponse response = new UserRegistertResponse();
		response.setEmailId("dilip@gmail.com");
		response.setFirstName("Dilip");
		response.setLastName("Singh");
		response.setMobileNumber("8826111377");

		return new ResponseEntity<>(response, HttpStatus.OK);

	}
}
