package com.m.weatherapp.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception ex) {
		return new ResponseEntity<String>(HttpStatus.SERVICE_UNAVAILABLE);
	}
}
