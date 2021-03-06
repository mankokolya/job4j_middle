package ru.job4j.concurrency.training.http;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class App {
    private static final String POSTS_URL = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(POSTS_URL))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//        System.out.println(response.body());

        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts = mapper.readValue(response.body(), new TypeReference<List<Post>>() {
        });
        posts.forEach(System.out::println);

    }
}
