package controller;

import java.io.File;
import java.time.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Issue;
import model.IssueStatus;
import service.IssueStore;
import util.ValidationUtil;

public class ReportIssueController {

    @FXML private TextField titleField;
    @FXML private TextArea descriptionArea;
    @FXML private ComboBox<String> categoryBox;

    private File selectedImage;

    @FXML
    public void initialize() {

        categoryBox.getItems().addAll(
                "Garbage",
                "Roads",
                "Electricity",
                "Water",
                "Public Safety",
                "Sanitation",
                "Other"
        );
    }

    @FXML
    private void uploadImage() {

        FileChooser chooser = new FileChooser();
        chooser.setTitle("Upload Image");
        chooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter(
                        "Images", "*.png", "*.jpg", "*.jpeg"
                )
        );

        selectedImage = chooser.showOpenDialog(
                titleField.getScene().getWindow()
        );
    }

    @FXML
    private void submit() {

        if (titleField.getText().isBlank()
                || descriptionArea.getText().isBlank()
                || categoryBox.getValue() == null) {

            ValidationUtil.showError(
                    "Please fill all required fields"
            );
            return;
        }

        Issue issue = new Issue(
                titleField.getText(),
                categoryBox.getValue(),
                IssueStatus.PENDING,
                LocalDate.now()
        );

        // 🔥 THIS IS THE KEY LINE
        IssueStore.addIssue(issue);

        ValidationUtil.showSuccess(
                "Issue submitted successfully!"
        );

        // Reset form
        titleField.clear();
        descriptionArea.clear();
        categoryBox.setValue(null);
        selectedImage = null;
    }
}
