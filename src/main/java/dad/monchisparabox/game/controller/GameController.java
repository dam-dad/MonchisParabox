package dad.monchisparabox.game.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.monchisparabox.game.data.MapDataController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

/**
 * Controlador principal del juego que gestiona la interfaz principal y otros controladores.
 */
public class GameController implements Initializable {

    @FXML
    private BorderPane view;

    private MapController mapController;

    private MapDataController mapDataController;

    /**
     * Constructor de la clase GameController.
     * Carga la vista principal del juego desde un archivo FXML.
     */
    public GameController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inicializa el controlador del juego.
     * Crea instancias de los controladores de mapas y datos del juego, y los asigna a la vista principal.
     * 
     * @param location  La ubicación relativa del objeto para inicializar.
     * @param resources Los recursos utilizados para localizar el objeto.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mapDataController = new MapDataController();

        mapController = new MapController();
        getView().setCenter(mapController.getView());
    }

    /**
     * Devuelve la vista principal del juego.
     * 
     * @return La vista principal del juego.
     */
    public BorderPane getView() {
        return view;
    }

    /**
     * Devuelve el controlador de datos del mapa del juego.
     * 
     * @return El controlador de datos del mapa del juego.
     */
    public MapDataController getMapDataController() {
        return mapDataController;
    }

    /**
     * Devuelve el controlador del mapa del juego.
     * 
     * @return El controlador del mapa del juego.
     */
    public MapController getMapController() {
        return mapController;
    }
}
