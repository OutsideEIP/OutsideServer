package com.outside.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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

import com.outside.database.Database;
import com.outside.database.Users;

@Component
class LoginService {
    @Autowired
    private Database database;

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

        String url = "https://oauth2.googleapis.com/token";
        RestTemplate restTemplate = new RestTemplate();
        String uri = new String();
        HttpHeaders headers = new HttpHeaders();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("code", authorizatioCode)
                .queryParam("client_id", dotenv.get("GOOGLE_CLIENT_ID"))
                .queryParam("client_secret", new String(dotenv.get("GOOGLE_CLIENT_SECRET")))
                .queryParam("grant_type", "authorization_code")
                .queryParam("redirect_uri", dotenv.get("GOOGLE_REDIRECT_URI"));

        // headers.add("application", "header1");
        try {
            uri = URLDecoder.decode(builder.toUriString(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                entity,
                String.class);

        System.out.println(response.getBody());

        return Map.of(
                "success", true,
                "user", "theo");
    }

    protected Map<String, Object> loginFacebook(String authorizatioCode) {
        String url = "https://graph.facebook.com/v14.0/oauth/access_token?";
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        // headers.add("Header", "header1");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("code", authorizatioCode)
                .queryParam("client_id", dotenv.get("FACEBOOK_CLIENT_ID"))
                .queryParam("client_secret", dotenv.get("FACEBOOK_CLIENT_SECRET"))
                .queryParam("redirect_uri", dotenv.get("FACEBOOK_REDIRECT_URI"));

        HttpEntity<?> entity = new HttpEntity<>(headers);
        HttpEntity<String> response = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        System.out.println(response.getBody());
        return Map.of(
                "success", true,
                "user", "theo");
    }
}
