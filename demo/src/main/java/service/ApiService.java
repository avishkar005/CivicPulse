package service;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import model.Issue;

public class ApiService {

    private static final String BASE_URL = "http://localhost:8080";
    private static String jwtToken;

    private ApiService() {}

    /* =========================
       AUTH
    ========================= */

    public static boolean login(String email, String password) {
        try {
            URL url = URI.create(BASE_URL + "/auth/login").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String body = "{"
                    + "\"email\":\"" + email + "\","
                    + "\"password\":\"" + password + "\""
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }

            int code = conn.getResponseCode();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            code == 200 ? conn.getInputStream() : conn.getErrorStream(),
                            StandardCharsets.UTF_8
                    )
            );

            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            String resp = response.toString();
            System.out.println("LOGIN RESPONSE: " + resp);

            if (code == 200 && !resp.isBlank()) {
                jwtToken = resp;
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /* =========================
       REGISTER
    ========================= */

    public static boolean register(String name, String email, String password) {
        try {
            URL url = URI.create(BASE_URL + "/auth/register").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String body = "{"
                    + "\"name\":\"" + name + "\","
                    + "\"email\":\"" + email + "\","
                    + "\"password\":\"" + password + "\""
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }

            return conn.getResponseCode() == 200 || conn.getResponseCode() == 201;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /* =========================
       ISSUE SUBMIT
    ========================= */

    public static void submitIssue(Issue issue) {
        try {
            URL url = URI.create(BASE_URL + "/issues").toURL();
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            if (jwtToken != null) {
                conn.setRequestProperty("Authorization", "Bearer " + jwtToken);
            }

            conn.setDoOutput(true);

            String body = "{"
                    + "\"title\":\"" + issue.getTitle() + "\","
                    + "\"description\":\"" + issue.getDescription() + "\","
                    + "\"category\":\"" + issue.getCategory() + "\","
                    + "\"city\":\"" + issue.getCity() + "\","
                    + "\"userEmail\":\"" + issue.getUserEmail() + "\""
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                os.write(body.getBytes(StandardCharsets.UTF_8));
            }

            System.out.println("ISSUE SUBMIT CODE: " + conn.getResponseCode());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* =========================
       ISSUE FETCH (My Reports) ✅
    ========================= */

    public static List<Issue> getMyIssues() {
        // until backend list API is wired — use local store
        return IssueStore.getAll();
    }

    /* ========================= */

    public static void loginWithGoogle() {
        try {
            Desktop.getDesktop().browse(
                    URI.create(BASE_URL + "/oauth2/authorization/google")
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isAuthenticated() {
        return jwtToken != null;
    }

    public static void logout() {
        jwtToken = null;
    }
    public static boolean checkOAuthStatus() {
    try {
        URL url = URI.create(BASE_URL + "/auth/oauth2/status").toURL();
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        if (conn.getResponseCode() == 200) {

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8)
            );

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                response.append(line);
            }

            String body = response.toString();

            if (body.contains("\"authenticated\":true")) {

                int tokenIndex = body.indexOf("\"token\":\"");
                if (tokenIndex != -1) {
                    int start = tokenIndex + 9;
                    int end = body.indexOf("\"", start);
                    jwtToken = body.substring(start, end);
                }

                return true;
            }
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return false;
}

}
