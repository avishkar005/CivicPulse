package controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class MapViewController {

    @FXML
    private WebView mapView;

    @FXML
    public void initialize() {
        mapView.getEngine().load(
                getClass().getResource("/map/leaflet.html").toExternalForm()
        );
    }
}
