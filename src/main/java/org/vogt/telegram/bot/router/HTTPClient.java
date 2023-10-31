package org.vogt.telegram.bot.router;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class HTTPClient {

    public static String post(String url, String payload) {
        String responseBody;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .headers("Content-Type", "application/x-www-form-urlencoded")
                    .POST(BodyPublishers.ofString(payload))
                    .build();
            HttpClient http = HttpClient.newBuilder().build();
            HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
            responseBody = response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            responseBody = "";
        }

        return responseBody;
    }

    public static String get(String url) {
        String body;

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
            HttpClient http = HttpClient.newBuilder().build();
            HttpResponse<String> response = http.send(request, BodyHandlers.ofString());
            body = response.body();
        } catch (URISyntaxException | IOException | InterruptedException e) {
            body = "";
        }

        return body;
    }

}
