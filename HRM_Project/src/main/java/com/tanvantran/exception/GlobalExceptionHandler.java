package com.tanvantran.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tanvantran.dto.ApiError;

import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleEntityNotFound(EntityNotFoundException ex, 
			HttpServletRequest request) {
		Map<String, Object> body = new HashMap<>();
		body.put("status", HttpStatus.NOT_FOUND.value());
		body.put("error", "Not Found");
		body.put("message_ex", ex.getMessage());
		body.put("path", request.getRequestURI());
			
			return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, 
			HttpServletRequest request) {
//		Map<String, Object> body = new HashMap<>();
//		body.put("status", HttpStatus.NOT_FOUND.value());
//		body.put("error", "Not Found");
//		body.put("message_ex", ex.getMessage());
//		body.put("path", request.getRequestURI());
		
		ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(), 
				"Not Found", ex.getMessage(), request.getRequestURI(), null);
			
			return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);

	}
	
	@ExceptionHandler(BadRequestException.class)
	public ResponseEntity<?> handleBadRequestException(BadRequestException ex, 
			HttpServletRequest request) {
		
		ApiError apiError = new ApiError(LocalDateTime.now(), HttpStatus.BAD_REQUEST.value(), 
				"Bad Request", ex.getMessage(), request.getRequestURI(), null);
			
			return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);

	}
}
