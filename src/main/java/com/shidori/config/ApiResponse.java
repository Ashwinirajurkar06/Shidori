package com.shidori.config;

import org.springframework.http.HttpStatus;

import com.shidori.model.Users;

public class ApiResponse<T> {

	private HttpStatus status;
    private String message;
    private T data;

    // Constructors
    public ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
    
 // Static helper methods for success and error responses
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

    public static <T> ApiResponse<T> success(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }
    public static <T> ApiResponse<T> error(HttpStatus status, String message) {
        return new ApiResponse<>(status, message, null);
    }
    public static <T> ApiResponse<T> error(HttpStatus status, String message, T data) {
        return new ApiResponse<>(status, message, data);
    }

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
    
    
}
