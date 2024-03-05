package dad.monchisparabox.game.controller;

import dad.auraengine.media.Music;
import dad.monchisparabox.App;
import dad.monchisparabox.game.Game;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controlador que gestiona el mapa del juego.
 */
public class MapController implements Initializable {

    @FXML
    private BorderPane view;

    private Game game;
    private Music music;

    /**
     * Constructor de la clase MapController.
     * Carga la vista del mapa desde un archivo FXML.
     */
    public MapController() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MapView.fxml"));
            loader.setController(this);
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Inicializa el controlador del mapa del juego.
     * Crea una instancia del juego con el mapa cargado.
     *
     * @param url            La ubicación relativa del objeto para inicializar.
     * @param resourceBundle Los recursos utilizados para localizar el objeto.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(() -> {
            setGame(new Game(App.getGameController().getMapDataController().getMapById(0)));
        });
    }

    /**
     * Devuelve la vista del mapa.
     *
     * @return La vista del mapa.
     */
    public BorderPane getView() {
        return view;
    }

    /**
     * Devuelve el juego asociado al mapa.
     *
     * @return El juego asociado al mapa.
     */
    public Game getGame() {
        return game;
    }

    /**
     * Establece el juego asociado al mapa.
     * Si hay un juego en ejecución, lo detiene antes de establecer el nuevo juego.
     * Luego inicia el nuevo juego y establece el centro de la vista en el mapa inicial del juego.
     *
     * @param game El nuevo juego a establecer.
     */
    public void setGame(Game game) {
        if (getGame() != null)
            getGame().stop();
        this.game = game;
        getGame().start();
        view.setCenter(getGame().getInitialMap());
    }
}
