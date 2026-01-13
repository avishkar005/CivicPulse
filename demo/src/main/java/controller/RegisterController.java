package controller;

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

        if (nameField.getText().isEmpty() ||
            emailField.getText().isEmpty() ||
            passwordField.getText().isEmpty() ||
            confirmPasswordField.getText().isEmpty()) {

            ValidationUtil.showError("All fields are required");
            return;
        }

        if (!passwordField.getText().equals(confirmPasswordField.getText())) {
            ValidationUtil.showError("Passwords do not match");
            return;
        }

        ApiService.register(
                nameField.getText(),
                emailField.getText(),
                passwordField.getText()
        );

        ValidationUtil.showSuccess("Account created successfully");
        SceneUtil.switchScene(nameField, "/fxml/login.fxml", "Login");
    }

    @FXML
    private void goLogin() {
        SceneUtil.switchScene(nameField, "/fxml/login.fxml", "Login");
    }
}
