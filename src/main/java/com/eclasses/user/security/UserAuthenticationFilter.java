package com.eclasses.user.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.eclasses.user.dto.UserDetailsDTO;
import com.eclasses.user.model.request.UserLoginRequest;
import com.eclasses.user.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class UserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationFilter.class);

	private UserService service;
	private Environment env;

	public UserAuthenticationFilter(UserService service, Environment env, AuthenticationManager authManager) {

		this.service = service;
		this.env = env;
		super.setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

		logger.info("Authentication Started...");

		UserLoginRequest credentials = null;

		try {
			credentials = new ObjectMapper().readValue(request.getInputStream(), UserLoginRequest.class);
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(credentials.getEmailId(), credentials.getPassword(), new ArrayList<>()));

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication auth)
			throws IOException, ServletException {

		logger.info("Authentication Successful...");
		String userId = ((User) auth.getPrincipal()).getUsername();

		UserDetailsDTO userDetails = service.getUserDetailsByEmailId(userId);

		String token = Jwts.builder()
				.setSubject(userDetails.getEmailId())
				.setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(env.getProperty("token.expiratin_time"))))
				.signWith(SignatureAlgorithm.HS512, env.getProperty("jwt.secret.token"))
				.compact();
		
		response.addHeader("token", token);
		response.addHeader("userId", userDetails.getEmailId());

	}

}
