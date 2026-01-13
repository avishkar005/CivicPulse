package config;

import model.User;

public class AppState {

    private static User currentUser;
    private static boolean darkMode = true;

    private AppState() {
    }

    // User session
    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    // Theme
    public static boolean isDarkMode() {
        return darkMode;
    }

    public static void toggleTheme() {
        darkMode = !darkMode;
    }
}
