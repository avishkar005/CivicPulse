package controller;

import java.io.File;
import java.time.LocalDate;

import config.AppState;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import model.Issue;
import model.IssueStatus;
import service.ApiService;
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
                descriptionArea.getText(),
                categoryBox.getValue(),
                "Pune",
                AppState.getUserEmail(),
                IssueStatus.PENDING,
                LocalDate.now()
        );

        // ✅ send to backend
        ApiService.submitIssue(issue);

        // ✅ ALSO store locally so MyReports screen can see it
        IssueStore.add(issue);

        ValidationUtil.showSuccess(
                "Issue submitted successfully!"
        );

        titleField.clear();
        descriptionArea.clear();
        categoryBox.setValue(null);
        selectedImage = null;
    }
}
