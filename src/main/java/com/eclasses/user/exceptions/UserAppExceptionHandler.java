package com.eclasses.user.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserAppExceptionHandler extends ResponseEntityExceptionHandler {
	/*
	 * @ExceptionHandler(value = { Exception.class }) public ResponseEntity<Object>
	 * handleAnyException(Exception ex, WebRequest request) { return new
	 * ResponseEntity<>(ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	 * 
	 * }
	 */

	@ExceptionHandler(value = { NullPointerException.class })
	public ResponseEntity<Object> handleNullPointerException(NullPointerException ex, WebRequest request) {

		String errorMessageDecription = ex.getLocalizedMessage();

		if (errorMessageDecription == null) {
			errorMessageDecription = ex.toString();
		}

		ErrorMessage error = new ErrorMessage();
		error.setTimeStamp(new Date());
		error.setErrorMessage(errorMessageDecription);

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	
	//handling specific group of Exceptions
	
	@ExceptionHandler(value = {ArrayIndexOutOfBoundsException.class, ArithmeticException.class})
	public ResponseEntity<Object> handleSpecificExceptionS(Exception ex, WebRequest request) {

		String errorMessageDecription = ex.getLocalizedMessage();

		if (errorMessageDecription == null) {
			errorMessageDecription = ex.toString();
		}

		ErrorMessage error = new ErrorMessage();
		error.setTimeStamp(new Date());
		error.setErrorMessage(errorMessageDecription);

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
