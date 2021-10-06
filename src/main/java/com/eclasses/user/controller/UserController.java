package com.eclasses.user.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

import com.eclasses.user.dto.UserDetailsDTO;
import com.eclasses.user.dto.UserRegisterDTO;
import com.eclasses.user.dto.UserUpdateDTO;
import com.eclasses.user.model.request.UserRegisterRequest;
import com.eclasses.user.model.request.UserUpdateRequest;
import com.eclasses.user.model.response.UserRegisterResponseModel;
import com.eclasses.user.model.response.UserRegistertResponse;
import com.eclasses.user.service.UserService;

@RestController
public class UserController {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService service;

	@Autowired
	private Environment env;

	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRegisterResponseModel> registerUser(@Valid @RequestBody UserRegisterRequest request) {

		log.info("\nEmail Id : " + request.getEmailId() + " \nPassword : " + request.getPassword() + " \nMobile Numer : " + request.getMobileNumber() + " \nFirst Name : "
				+ request.getFirstName() + " \nLast Name : " + request.getLastName());

		log.info("User Registration Started ");

		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

		UserRegisterDTO dto = service.registerUser(mapper.map(request, UserRegisterDTO.class));

		// Encrypt Password

		UserRegisterResponseModel reponse = mapper.map(dto, UserRegisterResponseModel.class);

		return new ResponseEntity<UserRegisterResponseModel>(reponse, HttpStatus.CREATED);
	}

	@PutMapping(path = "/update/{emailId}", consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public String updateUser(@Valid @RequestBody UserUpdateRequest request, @PathVariable String emailId) {

		log.info(" Update User : \nEmailId :" + emailId + " \nPassword : " + request.getPassword() + " \nMobile Numer : " + request.getMobileNumber() + " \nFirst Name : "
				+ request.getFirstName() + " \nLast Name : " + request.getLastName());

		log.info("User Details Updated.");

		UserUpdateDTO dto = new UserUpdateDTO();
		dto.setEmailId(emailId);
		dto.setFirstName(request.getFirstName());
		dto.setLastName(request.getLastName());
		dto.setMobileNumber(request.getMobileNumber());
		dto.setPassword(request.getPassword());

		String response = service.updateUser(dto);

		return response;

	}

	@GetMapping(path = "/{emailId}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<UserRegistertResponse> getUserDetails(@PathVariable String emailId) {

		log.info("Lookup User Id : " + emailId);

		UserRegistertResponse response = null;
		UserDetailsDTO userData = service.getUserDetailsByEmailId(emailId);

		if (userData != null) {

			response = new UserRegistertResponse();
			response.setFirstName(userData.getFirstName());
			response.setLastName(userData.getLastName());
			response.setMobileNumber(userData.getMobileNumber());
			return new ResponseEntity<UserRegistertResponse>(response, HttpStatus.OK);

		} else {

			return new ResponseEntity<UserRegistertResponse>(response, HttpStatus.NOT_FOUND);

		}

	}

	@GetMapping("/env/port")
	public String getConnectedPort() {
		return "Port Connected is " + env.getProperty("local.server.port");
	}

}
