package com.outside;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationDevHelpers {

	@GetMapping("/ping")
	public String index() {
		return "pong";
	}

}