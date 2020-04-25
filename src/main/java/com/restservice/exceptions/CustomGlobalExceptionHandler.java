package com.restservice.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	
	  public final ResponseEntity<Object> handleAllExceptions(CustomErrorDetails ex) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"from method argument not valid exception", ex.getMessage());
	    return new ResponseEntity<Object>(customErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	
	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"from method argument not valid exception", ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"from method argument not valid exception GL", ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
	}
	
	//user not found exception
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNootFoundException(UserNotFoundException ex, WebRequest request){
		CustomErrorDetails customErrorDetails = new CustomErrorDetails(new Date(),
				"user Not found exception", ex.getLocalizedMessage());
		
		return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
	}
}
