package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtil {

    private SceneUtil() {}

    public static void switchScene(Node node, String fxml, String title) {
        try {
            // ✅ Use context classloader (CRITICAL FIX)
            FXMLLoader loader = new FXMLLoader(
                    Thread.currentThread()
                          .getContextClassLoader()
                          .getResource(fxml.startsWith("/") ? fxml.substring(1) : fxml)
            );

            Parent root = loader.load();
            Scene scene = new Scene(root);

            scene.getStylesheets().add(
                    Thread.currentThread()
                          .getContextClassLoader()
                          .getResource("css/theme.css")
                          .toExternalForm()
            );

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            System.err.println("❌ FAILED TO LOAD SCENE: " + fxml);
            e.printStackTrace();
        }
    }
}
