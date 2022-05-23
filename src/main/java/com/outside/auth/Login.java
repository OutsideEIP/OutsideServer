package com.outside.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.context.annotation.Import;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;


import com.outside.database.Database;
import com.outside.database.Users;

@RequestMapping("/auth/login")
@RestController
public class Login {

    @Autowired
	Database database;

	@PostMapping("/natif")
	public String postNatifLogin(@RequestParam String email, @RequestParam String password
	) {
        List<Users> users = database.getUser(email);

        if (users == null || users.size() != 1)
            return "User not found";

        return users.get(0).getEmail();
        // String url = "jdbc:postgresql://localhost:5432/outside?user=postgres&password=password";
        // try {
        // 	Connection conn = DriverManager.getConnection(url);
        //     System.out.println(conn);
        //     System.out.println("Login ok");
        //     return "login natif in progress";
        // } catch (SQLException e) {
        //     e.printStackTrace();
        //     return "fail";
        // }

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
