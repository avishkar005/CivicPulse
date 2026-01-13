package controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.MockDataService;
import util.SceneUtil;
import util.ValidationUtil;

public class LoginController {

    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;

    @FXML
    private void handleLogin() {

        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            ValidationUtil.showError("Please enter email and password");
            return;
        }

        if (MockDataService.login(email, password)) {
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
}
