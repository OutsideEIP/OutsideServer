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

	@PostMapping("/login/natif")
	public String postNatifLogin(@RequestParam String email, @RequestParam String password) {

        String url = "jdbc:postgresql://localhost:5432/outside?user=postgres&password=password";
        try {
        	Connection conn = DriverManager.getConnection(url);
            System.out.println(conn);
            System.out.println("Login ok");
            return "login ok";
        } catch (SQLException e) {
            e.printStackTrace();
            return "fail";
        }
	}

	@PostMapping("/login/google")
	public String postGoogleLogin(@RequestParam String refreshToken) {
		return "login ok";
	}

}
