package com.outside.services;

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

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.outside.database.Database;
import com.outside.database.Users;

@Component
public class GoogleService {

    private Dotenv dotenv = Dotenv.load();

    public String getAccessToken(String authorizatioCode) {

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

        Map<String, String> map = new Gson().fromJson(response.getBody(), new TypeToken<Map<String, String>>() {
        }.getType());

        return map.get("access_token");
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
