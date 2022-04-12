package com.outside;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Import;

@RequestMapping("/auth")
@RestController
public class Auth {

	@PostMapping("/register")
	public String postRegister() {
		return "register ok";
	}

	@PostMapping("/login")
	public String postLogin() {
		return "login ok";
	}

}
