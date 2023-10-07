package com.investo.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import org.springframework.web.servlet.NoHandlerFoundException;
import jakarta.validation.ValidationException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CandleException.class)
	public ResponseEntity<ErrorDetails> candleExceptionHandler(CandleException ex, WebRequest req){
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorDetails> invalidInputExceptionHandler(InvalidInputException ex, WebRequest req){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
		
	}
	

	@ExceptionHandler(CandleIOException.class)
	public ResponseEntity<ErrorDetails> candleIOExceptionHandler(CandleIOException ex, WebRequest req){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
		
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorDetails> validationExceptionHandler(ValidationException ex, WebRequest req){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
		
	}
	

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exceptionHandler(Exception ex, WebRequest req){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
		
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundExceptionHandler(NoHandlerFoundException ex, WebRequest req){
		
		ErrorDetails errorDetails = new ErrorDetails();
		errorDetails.setTimeStamp(LocalDateTime.now());
		errorDetails.setMessage(ex.getMessage());
		errorDetails.setDetails(req.getDescription(false));
		
		return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
	}
	
}
