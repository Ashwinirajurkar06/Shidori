package com.shidori.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class MainController {

	@GetMapping("/index")
	public String getIndex() {
		return"Hello";
	}
}
