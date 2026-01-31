package config;

public class AppState {

    private static String jwtToken;
    private static String userEmail;

    private AppState() {}

    /* =========================
       AUTH STATE
    ========================= */

    public static void setJwtToken(String token) {
        jwtToken = token;
    }

    public static String getJwtToken() {
        return jwtToken;
    }

    public static void setUserEmail(String email) {
        userEmail = email;
    }

    public static String getUserEmail() {
        return userEmail;
    }

    public static boolean isLoggedIn() {
        return jwtToken != null && !jwtToken.isBlank();
    }

    public static void clear() {
        jwtToken = null;
        userEmail = null;
    }
}
