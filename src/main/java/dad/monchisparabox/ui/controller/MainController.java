package dad.monchisparabox.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class MainController implements Initializable {

    @FXML
    private BorderPane view;

    private MapController mapController;

    public MainController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapController = new MapController();
        getView().setCenter(mapController.getView());
    }

    public BorderPane getView() {
        return view;
    }

    public MapController getMapController() {
        return mapController;
    }
}