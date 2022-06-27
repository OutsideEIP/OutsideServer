package com.outside.services;

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

import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.outside.database.Database;
import com.outside.database.Users;

@Component
public class FacebookService {

    private Dotenv dotenv = Dotenv.load();

    public Map<String, Object> getAccessToken(String authorizatioCode) {
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

        try {

            HttpEntity<String> response = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    String.class);

            Map<String, String> map = new Gson().fromJson(response.getBody(), new TypeToken<Map<String, String>>() {
            }.getType());

            return Map.of(
                    "success", true,
                    "data", map.get("access_token"));
        } catch (RestClientException e) {
            // Map<String, Object> errorMap = new Gson().fromJson(
            //         e.getMessage().substring(e.getMessage().indexOf("\"",
            //                 0)).replaceAll("^\"|\"$", ""),
            //         new TypeToken<Map<String, Object>>() {
            //         }.getType());

            // Map<String, String> map = new
            // Gson().fromJson(errorMap.get("error").toString(), new TypeToken<Map<String,
            // String>>() {
            // }.getType());
            // System.out.println(map.get("message"));

            return Map.of(
                    "success", false,
                    "errorMsg", "error");
        }
    }
}
