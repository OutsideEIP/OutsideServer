package com.outside.auth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@OpenAPIDefinition(info = @Info(title = "Outside swagger", version = "1.0", description = "Swagger Information"))
@RequestMapping("/auth/register")
@RestController
public class Register {

	public String register() {
		return "register";
	}

	@Operation(summary = "Get register", responses = {
		@ApiResponse(responseCode= "200", description = "description", content = @Content(schema = @Schema(example = "{\"foo\": \"bar\",\"baz\": true}")))
	})
	@GetMapping
	public String getRegister() {
		String name = register();
		return "Register routes " + name + "\n";
	}

	@Operation(summary = "Register with our app",responses = {
		@ApiResponse(responseCode= "200", description = "Success register"),
		@ApiResponse(responseCode= "400", description = "Failure register")
	})
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

	@Operation(
		summary = "Register with google",
		responses = {
			@ApiResponse(responseCode= "200", description = "Success register"),
			@ApiResponse(responseCode= "400", description = "Failure register"),
		}
	)
	@PostMapping("/google")
	public String postGoogleRegister(@RequestParam String refreshToken) {
		return "Register google in progress";
	}

	@Operation(
		summary = "Register with facebook",
		responses = {
			@ApiResponse(responseCode= "200", description = "Success register"),
			@ApiResponse(responseCode= "400", description = "Failure register"),
		}
	)
	@PostMapping("/facebook")
	public String postFacebookRegister(@RequestParam String refreshToken) {
		return "Register facebook in progress";
	}

	@Operation(summary = "Register with twitter", responses = {
		@ApiResponse(responseCode= "200", description = "Success register"),
		@ApiResponse(responseCode= "400", description = "Failure register"),
	})
	@PostMapping("/twitter")
	public String postTwitterRegister(@RequestParam String refreshToken) {
		return "Register twitter in progress :" + refreshToken;
	}

}
