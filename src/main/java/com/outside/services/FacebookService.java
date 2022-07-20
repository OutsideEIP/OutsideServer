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
import com.nimbusds.jose.shaded.json.JSONObject;
import com.nimbusds.jose.shaded.json.parser.JSONParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.outside.database.Database;
import com.outside.database.Users;

import aj.org.objectweb.asm.TypeReference;

@Component
public class FacebookService {

    private Dotenv dotenv = Dotenv.load();

    public HttpRequest.BodyPublisher encode(HashMap<String, String> values) {
        String form = values
                .entrySet()
                .stream()
                .map(entry -> String.join("=",
                        URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8),
                        URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8)))
                .collect(Collectors.joining("&"));
        return HttpRequest.BodyPublishers.ofString(form);
    }

    // @SuppressWarnings("unchecked")
    public Map<String, Object> getAccessToken(String authorizatioCode) {
        String url = "https://graph.facebook.com/v14.0/oauth/access_token?";
        HttpResponse<String> response = null;
        HttpClient client = HttpClient.newHttpClient();
        var values = new HashMap<String, String>() {
            {
                put("code", authorizatioCode);
                put("client_id", dotenv.get("FACEBOOK_CLIENT_ID"));
                put("client_secret", dotenv.get("FACEBOOK_CLIENT_SECRET"));
                put("redirect_uri", dotenv.get("FACEBOOK_REDIRECT_URI"));
            }
        };

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

        System.out.println(response.body());
        Map<String, Object> map = new Gson().fromJson(response.body(), new TypeToken<Map<String, Object>>() {
        }.getType());
        System.out.println(map);
        if (map.get("error") != null) {
            return Map.of(
                    "success", false,
                    "errorMsg", ((Map<String, Object>) map.get("error")).get("message"));
        } else
            return Map.of(
                    "success", true,
                    "errorMsg", map.get("accessToken"));
    }
}
