package com.civicpulse.client;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;

public class ApiClient {

    private static final String BASE_URL = "http://localhost:8080";
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();

    public static String register(String name, String email, String password) throws IOException {

        UserRequest bodyObj = new UserRequest(name, email, password);
        String json = gson.toJson(bodyObj);

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + "/auth/register")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public static String login(String email, String password) throws IOException {

        LoginRequest bodyObj = new LoginRequest(email, password);
        String json = gson.toJson(bodyObj);

        RequestBody body = RequestBody.create(
                json,
                MediaType.parse("application/json")
        );

        Request request = new Request.Builder()
                .url(BASE_URL + "/auth/login")
                .post(body)
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string(); // JWT token comes here
    }
}
