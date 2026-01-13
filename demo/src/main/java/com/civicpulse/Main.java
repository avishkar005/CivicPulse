package com.civicpulse;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/fxml/login.fxml")
        );

        Parent root = loader.load();

        Scene scene = new Scene(root, 1200, 800);

        // ✅ CORRECT WAY TO LOAD CSS
        scene.getStylesheets().add(
                getClass().getResource("/css/theme.css").toExternalForm()
        );

        stage.setTitle("CivicPulse");
        stage.setScene(scene);
        stage.setMinWidth(1100);
        stage.setMinHeight(700);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
