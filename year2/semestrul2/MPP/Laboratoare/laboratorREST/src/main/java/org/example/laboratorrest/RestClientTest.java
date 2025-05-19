package org.example.laboratorrest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClientTest {

    public static void main(String[] args) throws IOException {
        testPostProba();
        testGetProbe();
    }

    public static void testPostProba() throws IOException {
        URL url = new URL("http://localhost:8080/api/probe");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        String jsonInput = """
            {
              "nume": "400m",
              "categorie": "Open",
              "durata": 60
            }
        """;

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInput.getBytes();
            os.write(input, 0, input.length);
        }

        int code = connection.getResponseCode();
        System.out.println("POST /api/probe status: " + code);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String response;
            while ((response = br.readLine()) != null) {
                System.out.println(response);
            }
        }
    }

    public static void testGetProbe() throws IOException {
        URL url = new URL("http://localhost:8080/api/probe");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int code = connection.getResponseCode();
        System.out.println("GET /api/probe status: " + code);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String response;
            while ((response = br.readLine()) != null) {
                System.out.println(response);
            }
        }
    }
}