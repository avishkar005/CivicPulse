package service;

import model.Issue;

public class ApiService {

    private ApiService() {}

    public static boolean login(String email, String password) {
        return email != null && email.contains("@") && password.length() >= 6;
    }

    public static boolean register(String name, String email, String password) {
        return true; // mock
    }

    public static void submitIssue(Issue issue) {
        System.out.println("Issue submitted (mock): " + issue.getTitle());
    }
}
