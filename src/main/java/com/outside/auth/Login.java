package com.outside;

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

@RequestMapping("/auth/login")
@RestController
public class Login {


	@PostMapping("/natif")
	public String postNatifLogin(@RequestParam String email, @RequestParam String password
	) {

        String url = "jdbc:postgresql://localhost:5432/outside?user=postgres&password=password";
        try {
        	Connection conn = DriverManager.getConnection(url);
            System.out.println(conn);
            System.out.println("Login ok");
            return "login natif in progress";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
	}

	@PostMapping("/google")
	public String postGoogleLogin(@RequestParam String refreshToken) {
		return "login google in progress";
	}

	@PostMapping("/facebook")
	public String postFacebookLogin(@RequestParam String refreshToken) {
		return "login facebook in progress";
	}

	@PostMapping("/twitter")
	public String postTwitterLogin(@RequestParam String refreshToken) {
		return "login twitter in progress";
	}

}
