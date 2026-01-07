package com.shidori.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shidori.config.ApiResponse;

import com.shidori.model.Users;

import com.shidori.service.UserService;

@RestController
@RequestMapping("/auth")
public class MainController {
	
	@Autowired
	private UserService userservice;
	
	  
	  @PostMapping("/saveuser")
	  public ApiResponse<Users> SaveUser(@RequestBody Users user){
		try {
		  Users data=userservice.SaveUser(user);
		  
		  return ApiResponse.success(HttpStatus.CREATED,
				  					"Data stored Successfully");
		}catch (Exception e) {
			// TODO: handle exception
			return ApiResponse.error(HttpStatus.BAD_REQUEST,
									e.getMessage());
		}
	  }
	 
}
