package com.outside.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;


import com.outside.database.Database;
import com.outside.database.Users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RequestMapping("/auth/login")
@RestController
public class Login {

    @Autowired
	Database database;

	@Operation(
		summary = "Login with our app",
		responses = {
			@ApiResponse(responseCode= "200", description = "Success login"),
			@ApiResponse(responseCode= "400", description = "Failure login"),
		}
	)
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

	@Operation(
		summary = "Login with googleh",
		responses = {
			@ApiResponse(responseCode= "200", description = "Success login"),
			@ApiResponse(responseCode= "400", description = "Failure login"),
		}
	)
	@PostMapping("/google")
	public String postGoogleLogin(@RequestParam String refreshToken) {
		return "login google in progress";
	}

	@Operation(
		summary = "Login with facebook",
		responses = {
			@ApiResponse(responseCode= "200", description = "Success login"),
			@ApiResponse(responseCode= "400", description = "Failure login"),
		}
	)
	@PostMapping("/facebook")
	public String postFacebookLogin(@RequestParam String refreshToken) {
		return "login facebook in progress";
	}

	@Operation(
		summary = "Login with twitter",
		responses = {
			@ApiResponse(responseCode= "200", description = "Success login"),
			@ApiResponse(responseCode= "400", description = "Failure login"),
		}
	)
	@PostMapping("/twitter")
	public String postTwitterLogin(@RequestParam String refreshToken) {
		return "login twitter in progress";
	}

}
