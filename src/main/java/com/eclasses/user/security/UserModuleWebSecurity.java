package com.eclasses.user.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.eclasses.user.service.UserService;

@Configuration
@EnableWebSecurity
public class UserModuleWebSecurity extends WebSecurityConfigurerAdapter {

	private static final Logger logger = LoggerFactory.getLogger(UserModuleWebSecurity.class);

	private Environment env;
	private UserService service;
	private BCryptPasswordEncoder encoder;

	@Autowired
	public UserModuleWebSecurity(Environment env, UserService service, BCryptPasswordEncoder encoder) {
		this.env = env;
		this.service = service;
		this.encoder = encoder;
	}

	@Override
	protected void configure(HttpSecurity security) {

		logger.info("User Module Scurity Validation in progress......");

		try {
			security.csrf().disable();
			security.authorizeRequests().antMatchers("/**").hasIpAddress(env.getProperty("zuul.gateway.ip")).and().addFilter(getUserAuthenticationFilter());
			security.authorizeRequests().antMatchers("/user-service/**").permitAll();
			security.headers().frameOptions().disable();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private UserAuthenticationFilter getUserAuthenticationFilter() {
		
		logger.info("User Module Scurity Validation : getUserAuthenticationFilter ");

		UserAuthenticationFilter authFilter = null;

		try {
			authFilter = new UserAuthenticationFilter(service, env, authenticationManager());
			authFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
			// authFilter.setAuthenticationManager(authenticationManager());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return authFilter;
	}

	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("User Authentication Started....");
		auth.userDetailsService(service).passwordEncoder(encoder);
	}

}
