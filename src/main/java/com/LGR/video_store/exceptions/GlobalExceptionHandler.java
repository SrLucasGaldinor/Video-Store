package com.LGR.video_store.exceptions;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex,
															    HttpServletRequest request) {
		ErrorResponse error = new ErrorResponse(Instant.now(),
												HttpStatus.NOT_FOUND.value(),
												"Resource not found!",
												ex.getMessage(),
												request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); 
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex,
																 HttpServletRequest request) {
		
		String message = ex.getBindingResult()
						   .getFieldErrors()
						   .get(0)
						   .getDefaultMessage();
				
		ErrorResponse error = new ErrorResponse(Instant.now(),
												HttpStatus.BAD_REQUEST.value(),
												"Validation error!",
												message,
												request.getRequestURI());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
}
