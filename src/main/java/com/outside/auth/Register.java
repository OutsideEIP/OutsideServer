package com.outside.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.annotation.Import;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

@RequestMapping("/auth/register")
@RestController
public class Register {

	@GetMapping
	public String getRegister() {
		return "Register routes";
	}

	@PostMapping("/natif")
	public String postNatifRegister(@RequestParam String email, @RequestParam String password, @RequestParam String confirmPassword, @RequestParam String accountType) {

        String url = "jdbc:postgresql://localhost:5432/outside?user=postgres&password=password";
        try {
        	Connection conn = DriverManager.getConnection(url);
            return "Register natif in progress";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
	}

	@PostMapping("/google")
	public String postGoogleRegister(@RequestParam String refreshToken) {
		return "Register google in progress";
	}

	@PostMapping("/facebook")
	public String postFacebookRegister(@RequestParam String refreshToken) {
		return "Register facebook in progress";
	}

	@PostMapping("/twitter")
	public String postTwitterRegister(@RequestParam String refreshToken) {
		return "Register twitter in progress";
	}

}
