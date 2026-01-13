package controller;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.ValidationUtil;

public class ProfileController {

    @FXML private TextField nameField, emailField;
    @FXML private DatePicker dobPicker;
    @FXML private PasswordField passwordField;

    @FXML
    public void initialize() {
        nameField.setText("Avishkar Choundkar");
        emailField.setText("avishkar@gmail.com");
    }

    @FXML
    private void saveProfile() {
        ValidationUtil.showSuccess("Profile updated successfully");
    }
}
