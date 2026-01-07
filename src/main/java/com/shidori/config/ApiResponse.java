package com.shidori.config;

import org.springframework.http.HttpStatus;

import com.shidori.model.Users;

public class ApiResponse<T> {

	private HttpStatus status;
	private Boolean success;
    private String message;
    private T data;

    // Constructors
    public ApiResponse(HttpStatus status,boolean success, String message, T data) {
        this.status = status;
        this.success=success;
        this.message = message;
        this.data = data;
    }
    
 // Static helper methods for success and error responses
    public static <T> ApiResponse<T> success(HttpStatus status,boolean success, String message, T data) {
        return new ApiResponse<>(status,success, message, data);
    }

    public static <T> ApiResponse<T> success(HttpStatus status ,boolean success, String message) {
        return new ApiResponse<>(status,success, message, null);
    }
    public static <T> ApiResponse<T> error(HttpStatus status ,boolean success, String message) {
        return new ApiResponse<>(status,success, message, null);
    }
    public static <T> ApiResponse<T> error(HttpStatus status ,boolean success, String message, T data) {
        return new ApiResponse<>(status,success, message, data);
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
