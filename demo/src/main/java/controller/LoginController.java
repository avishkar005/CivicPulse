package controller;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.ApiService;
import util.SceneUtil;
import util.ValidationUtil;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin() {

        String email = emailField.getText();
        String password = passwordField.getText();

        if (email == null || email.isBlank()
                || password == null || password.isBlank()) {

            ValidationUtil.showError("Please enter email and password");
            return;
        }

        boolean success = ApiService.login(email, password);

        if (success) {
            SceneUtil.switchScene(
                    emailField,
                    "/fxml/dashboard.fxml",
                    "CivicPulse - Dashboard"
            );
        } else {
            ValidationUtil.showError("Invalid email or password");
        }
    }

    @FXML
    private void goToRegister() {
        SceneUtil.switchScene(
                emailField,
                "/fxml/register.fxml",
                "Register - CivicPulse"
        );
    }

    /**
     * 🔐 Google Login (OAuth)
     */
    
@FXML
private void handleGoogleLogin() {

    // 1️⃣ Open browser
    ApiService.loginWithGoogle();

    // 2️⃣ Poll backend status
    Task<Boolean> task = new Task<>() {
        @Override
        protected Boolean call() throws Exception {

            for (int i = 0; i < 60; i++) { // wait up to 60s
                if (ApiService.checkOAuthStatus()) {
                    return true;
                }
                Thread.sleep(1000);
            }
            return false;
        }
    };

    task.setOnSucceeded(e -> {
        if (task.getValue()) {
            SceneUtil.switchScene(
                    emailField,
                    "/fxml/dashboard.fxml",
                    "CivicPulse - Dashboard"
            );
        } else {
            ValidationUtil.showError("Google login timed out");
        }
    });

    new Thread(task).start();
}

}
