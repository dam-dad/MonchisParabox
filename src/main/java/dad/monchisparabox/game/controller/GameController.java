package dad.monchisparabox.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.monchisparabox.game.data.MapDataController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class GameController implements Initializable {

    @FXML
    private BorderPane view;

    private MapController mapController;

    private MapDataController mapDataController;


    public GameController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/GameView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapDataController = new MapDataController();

        mapController = new MapController();
        getView().setCenter(mapController.getView());
    }

    public BorderPane getView() {
        return view;
    }

    public MapDataController getMapDataController() {
        return mapDataController;
    }

    public MapController getMapController() {
        return mapController;
    }
}