package util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneUtil {

    private SceneUtil() {}

    public static void switchScene(Node node, String fxml, String title) {
        try {
            FXMLLoader loader =
                    new FXMLLoader(SceneUtil.class.getResource(fxml));

            Scene scene = new Scene(loader.load());

            // ✅ CORRECT CSS LOADING
            scene.getStylesheets().add(
                    SceneUtil.class
                            .getResource("/css/theme.css")
                            .toExternalForm()
            );

            Stage stage = (Stage) node.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
