package com.civicpulse.client;

public class SessionManager {

    private static String jwt;

    public static void setToken(String token) {
        jwt = token;
    }

    public static String getToken() {
        return jwt;
    }
}
