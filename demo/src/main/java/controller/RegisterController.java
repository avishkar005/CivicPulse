package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.ApiService;
import util.SceneUtil;
import util.ValidationUtil;

public class RegisterController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private void handleRegister() {

        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            ValidationUtil.showError("All fields are required");
            return;
        }

        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            ValidationUtil.showError("Please enter a valid email address");
            return;
        }

        if (!password.equals(confirmPassword)) {
            ValidationUtil.showError("Passwords do not match");
            return;
        }

        if (password.length() < 8) {
            ValidationUtil.showError("Password must be at least 8 characters long");
            return;
        }

        Task<Boolean> registerTask = new Task<Boolean>() {
            @Override
            protected Boolean call() {
                return ApiService.register(name, email, password);
            }
        };

        registerTask.setOnSucceeded(event -> {
            if (registerTask.getValue()) {
                ValidationUtil.showSuccess("Account created successfully");
                SceneUtil.switchScene(
                        nameField,
                        "/fxml/login.fxml",
                        "Login - CivicPulse"
                );
            } else {
                ValidationUtil.showError("Registration failed. Email may already exist.");
            }
        });

        registerTask.setOnFailed(event ->
                ValidationUtil.showError("Unable to register. Server not reachable.")
        );

        new Thread(registerTask).start();
    }

    @FXML
    private void goLogin() {
        SceneUtil.switchScene(
                nameField,
                "/fxml/login.fxml",
                "Login - CivicPulse"
        );
    }

    /**
     * 🔐 Google Signup (DESKTOP SAFE)
     */
    @FXML
    private void handleGoogleLogin() {

        // 1️⃣ Open browser
        ApiService.loginWithGoogle();

        // 2️⃣ Poll backend OAuth STATUS (NOT oauth2/success)
        Task<Boolean> oauthTask = new Task<Boolean>() {
            @Override
            protected Boolean call() throws Exception {

                for (int i = 0; i < 60; i++) {
                    if (ApiService.checkOAuthStatus()) {
                        return true;
                    }
                    Thread.sleep(1000);
                }
                return false;
            }
        };

        oauthTask.setOnSucceeded(event -> {
            if (oauthTask.getValue()) {
                SceneUtil.switchScene(
                        nameField,
                        "/fxml/dashboard.fxml",
                        "CivicPulse - Dashboard"
                );
            } else {
                ValidationUtil.showError("Google signup timed out");
            }
        });

        oauthTask.setOnFailed(event ->
                ValidationUtil.showError("Google signup failed")
        );

        new Thread(oauthTask).start();
    }
}
