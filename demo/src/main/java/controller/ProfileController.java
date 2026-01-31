package controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import util.SceneUtil;
import util.ValidationUtil;

public class ProfileController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private DatePicker dobPicker;
    @FXML private PasswordField passwordField;

    @FXML
    public void initialize() {

        // Dummy user data (later can come from backend / session)
        nameField.setText("Avishkar Choundkar");
        emailField.setText("avishkar@gmail.com");

        // Usually email should not be editable
        emailField.setEditable(false);
    }

    @FXML
    private void saveProfile() {

        if (nameField.getText().isBlank()) {
            ValidationUtil.showError("Name cannot be empty");
            return;
        }

        ValidationUtil.showSuccess("Profile updated successfully");
    }

    /* 🔥 LOGOUT FUNCTIONALITY */
    @FXML
    private void logout(javafx.event.ActionEvent event) {

        SceneUtil.switchScene(
                (Node) event.getSource(),
                "/fxml/login.fxml",
                "CivicPulse - Login"
        );
    }
    @FXML
    private void logout1(javafx.event.ActionEvent event) {

    // ✅ ADD THESE TWO LINES
    service.ApiService.logout();
    config.AppState.setUserEmail(null);

    SceneUtil.switchScene(
            (Node) event.getSource(),
            "/fxml/login.fxml",
            "CivicPulse - Login"
    );
}

}
