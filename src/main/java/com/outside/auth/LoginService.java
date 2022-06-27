package com.outside.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import io.github.cdimascio.dotenv.Dotenv;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.outside.database.Database;
import com.outside.services.GoogleService;
import com.outside.services.FacebookService;
import com.outside.database.Users;

@Component
class LoginService {
    @Autowired
    private Database database;
    @Autowired
    private GoogleService googleService;
    @Autowired
    private FacebookService facebookService;

    private Dotenv dotenv = Dotenv.load();

    protected Map<String, Object> loginNatif(String email, String password) {
        List<Users> users = database.getUser(email);

        if (users == null || users.isEmpty())
            return Map.of(
                    "success", false,
                    "errorMsg", "User not found");
        if (users.get(0).getToken().equals(password) == false) {
            return Map.of(
                    "success", false,
                    "errorMsg", "Password incorrect");
        }
        return Map.of(
                "success", true,
                "user", users.get(0));
    }

    protected Map<String, Object> loginGoogle(String authorizatioCode) {
        Map<String, Object> accessToken = googleService.getAccessToken(authorizatioCode);
        boolean success = ((boolean) accessToken.get("success"));
        return Map.of(
                "success", success,
                success ? "data" : "errorMsg", success ? accessToken.get("data") : accessToken.get("errorMsg"));
    }

    protected Map<String, Object> loginFacebook(String authorizatioCode) {
        Map<String, Object> accessToken = facebookService.getAccessToken(authorizatioCode);
        boolean success = ((boolean) accessToken.get("success"));
        return Map.of(
                "success", success,
                success ? "data" : "errorMsg", success ? accessToken.get("data") : accessToken.get("errorMsg"));
    }
}
