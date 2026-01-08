package com.shidori.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ApiResponse<Object>> handleValidationException(ValidationException ex) {

		Map<String, Object> errorBody = new HashMap<>();
		errorBody.put("errors", ex.getErrors());

		ApiResponse<Object> response = new ApiResponse<>(400, false, "Validation failed.", errorBody);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

}
