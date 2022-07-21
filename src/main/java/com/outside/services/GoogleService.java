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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;
import com.outside.database.Database;
import com.outside.database.Users;

@Component
public class GoogleService {

    private Dotenv dotenv = Dotenv.load();

    public HttpRequest.BodyPublisher encode(Map<String, String> values) {
        String form = values
                .entrySet()
                .stream()
                .map(entry -> String.join("=",
                        URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8),
                        URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8)))
                .collect(Collectors.joining("&"));
        return HttpRequest.BodyPublishers.ofString(form);
    }

    public Map<String, Object> getAccessToken(String authorizatioCode) {
        String url = "https://oauth2.googleapis.com/token";
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        var values = Map.of("code", authorizatioCode,
                "grant", "authorizatioCode",
                "client_id", dotenv.get("GOOGLE_CLIENT_ID"),
                "client_secret", dotenv.get("GOOGLE_CLIENT_SECRET"),
                "redirect_uri", dotenv.get("GOOGLE_REDIRECT_URI"));

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers("Content-Type", "application/x-www-form-urlencoded")
                .POST(this.encode(values))
                .build();

        try {
            response = client.send(request,
                    HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        Map<String, Object> map = new Gson().fromJson(response.body(), new TypeToken<Map<String, Object>>() {
        }.getType());

        return map.get("error_description") != null ? Map.of("success", false, "errorMsg", map.get("error_description"))
                : Map.of("success", false, "errorMsg", "error");
    }
}