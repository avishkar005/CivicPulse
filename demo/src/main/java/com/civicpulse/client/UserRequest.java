package com.civicpulse.client;

public class UserRequest {
    public String name;
    public String email;
    public String password;

    public UserRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
