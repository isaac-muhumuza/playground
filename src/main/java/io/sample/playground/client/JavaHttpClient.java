package io.sample.playground.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.context.annotation.ApplicationScope;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublisher;
import static java.net.http.HttpRequest.newBuilder;

@ApplicationScope
public class JavaHttpClient {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";     //sample url
    private final HttpClient client;
    private final ObjectMapper objectMapper;

    public JavaHttpClient() {
        this.client = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public HttpRequest getRequest(String url) throws URISyntaxException {
        return newBuilder()
                .uri(new URI(getUrl(url)))
                .GET()
                .build();
    }

    public HttpRequest postRequest(String url, BodyPublisher bodyPublisher) throws URISyntaxException {
        return newBuilder()
                .uri(new URI(getUrl(url)))
                .POST(bodyPublisher)
                .build();
    }

    public <T> T getResponse(HttpRequest request, Class<T> clazz) throws IOException, InterruptedException {
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return objectMapper.readValue(response.body(), clazz);
    }

    @NotNull
    private static String getUrl(String url) {
        if (!url.isBlank() && url.startsWith("/")) {
            url = url.substring(1);
        }
        return BASE_URL + url;
    }
}
