package com.outside.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

import com.outside.database.Users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RequestMapping("/auth/login")
@RestController
public class LoginController {

    @Autowired
    private LoginService loginService = new LoginService();

    @Operation(summary = "Login with our app", responses = {
            @ApiResponse(responseCode = "200", description = "Success login"),
            @ApiResponse(responseCode = "400", description = "Failure login"),
    })
    @PostMapping("/natif")
    public String postNatifLogin(@RequestParam String email, @RequestParam String password) {
        Map<String, Object> rslt = loginService.loginNatif(email, password);

        return (String)((boolean)rslt.get("success") ? ((Users)rslt.get("user")).getEmail(): rslt.get("errorMsg"));
    }

    @Operation(summary = "Login with google", responses = {
            @ApiResponse(responseCode = "200", description = "Success login"),
            @ApiResponse(responseCode = "400", description = "Failure login"),
    })
    @PostMapping("/google")
    public String postGoogleLogin(@RequestParam String authorizatioCcode) {
        loginService.loginGoogle(authorizatioCcode);
        return "login google in progress";
    }

    @Operation(summary = "Login with facebook", responses = {
            @ApiResponse(responseCode = "200", description = "Success login"),
            @ApiResponse(responseCode = "400", description = "Failure login"),
    })
    @PostMapping("/facebook")
    public String postFacebookLogin(@RequestParam String authorizatioCcode) {
        loginService.loginFacebook(authorizatioCcode);
        return "login facebook in progress";
    }

    @Operation(summary = "Login with twitter", responses = {
            @ApiResponse(responseCode = "200", description = "Success login"),
            @ApiResponse(responseCode = "400", description = "Failure login"),
    })
    @PostMapping("/twitter")
    public String postTwitterLogin(@RequestParam String authorizatioCcode) {
        return "login twitter in progress";
    }

}
