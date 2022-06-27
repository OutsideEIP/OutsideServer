package com.outside.auth;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<String> postNatifLogin(@RequestParam String email, @RequestParam String password) {
        Map<String, Object> rslt = loginService.loginNatif(email, password);
        boolean success = (boolean) rslt.get("success");

        return new ResponseEntity<>((String) (success ? rslt.get("data") : rslt.get("errorMsg")),
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Login with google", responses = {
            @ApiResponse(responseCode = "200", description = "Success login"),
            @ApiResponse(responseCode = "400", description = "Failure login"),
    })
    @PostMapping("/google")
    public ResponseEntity<String> postGoogleLogin(@RequestParam String authorizatioCcode) {
        Map<String, Object> rslt = loginService.loginGoogle(authorizatioCcode);
        boolean success = (boolean) rslt.get("success");

        return new ResponseEntity<>((String) (success ? rslt.get("data") : rslt.get("errorMsg")),
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Login with facebook", responses = {
            @ApiResponse(responseCode = "200", description = "Success login"),
            @ApiResponse(responseCode = "400", description = "Failure login"),
    })
    @PostMapping("/facebook")
    public ResponseEntity<String> postFacebookLogin(@RequestParam String authorizatioCcode) {
        Map<String, Object> rslt = loginService.loginFacebook(authorizatioCcode);
        boolean success = (boolean) rslt.get("success");

        return new ResponseEntity<>((String) (success ? rslt.get("data") : rslt.get("errorMsg")),
                success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

}
