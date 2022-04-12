package com.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/auth")
@RestController
public class AuthRegister {

	@GetMapping("/register")
	public String getRegister() {
		return "register ok";
	}

}