package com.outside.services;

import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.stream.Collectors;

@Component
public class ApiService {

    Dotenv dotenv = Dotenv.load();

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
}
