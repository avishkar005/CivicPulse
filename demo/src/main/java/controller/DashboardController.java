package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

public class DashboardController {

    @FXML
    private StackPane contentArea;
    @FXML
    private void openCityFeed() {
    loadPage("/fxml/city_feed.fxml");
     }


    @FXML
    private void openReportIssue() {
        loadPage("/fxml/report_issue.fxml");
    }

    @FXML
    private void openMyReports() {
        loadPage("/fxml/my_reports.fxml");
    }

    @FXML
    private void openMapView() {
        loadPage("/fxml/map_view.fxml");
    }

    @FXML
    private void openProfile() {
        loadPage("/fxml/profile.fxml");
    }

    private void loadPage(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(getClass().getResource(fxmlPath));
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
