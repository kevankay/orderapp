package com.capgemini.orderapp.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.orderapp.entity.ErrorMessage;
import com.capgemini.orderapp.exception.OrderIdAlreadyExistException;
import com.capgemini.orderapp.exception.OrderNotFoundException;



@ControllerAdvice
@RestController
public class ExceptionController {
	
	@ExceptionHandler(value = OrderNotFoundException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> customerNotFoundException(
			OrderNotFoundException customerNotFoundException, HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(),
				customerNotFoundException.getMessage(), LocalDateTime.now(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(value = OrderIdAlreadyExistException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> registrationFailedException(
			OrderIdAlreadyExistException customerNotFoundException, HttpServletRequest request) {
		ErrorMessage errorMessage = new ErrorMessage(request.getRequestURI(),
				customerNotFoundException.getMessage(), LocalDateTime.now(),  HttpStatus.FOUND);
		System.out.println(errorMessage);
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.FOUND);
	}

}



