package controller;

import config.AppState;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import service.ApiService;
import util.SceneUtil;

public class DashboardController {

    @FXML
    private StackPane contentArea;

    // 🔥 LOAD DEFAULT PAGE
    @FXML
    private void initialize() {
        loadPage("/fxml/report_issue.fxml");
    }

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

    // ✅ LOGOUT FIX
    @FXML
    private void handleLogout() {

        ApiService.logout();          // clear JWT
        AppState.setUserEmail(null);  // clear user

        SceneUtil.switchScene(
                contentArea,
                "/fxml/login.fxml",
                "Login - CivicPulse"
        );
    }

    private void loadPage(String fxmlPath) {
        try {
            Node view = FXMLLoader.load(
                    getClass().getResource(fxmlPath)
            );
            contentArea.getChildren().setAll(view);
        } catch (Exception e) {
            System.err.println("❌ Failed to load: " + fxmlPath);
            e.printStackTrace();
        }
    }
}
