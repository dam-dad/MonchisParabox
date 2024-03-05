package dad.monchisparabox;

import dad.monchisparabox.game.controller.GameController;
import dad.monchisparabox.ui.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Clase principal que inicia la aplicación Monchi's Parabox.
 */
public class App extends Application {

    private static Stage primaryStage;

    // Controladores
    private static MainController controlador = new MainController();
    private static GameController gameController;

    @Override
    public void start(Stage primaryStage) {
        App.primaryStage = primaryStage;

        primaryStage.setTitle("Monchi's Parabox");
        primaryStage.setScene(new Scene(controlador.getView(), 928, 522));
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image("/assets/icono.png"));
        primaryStage.show();
    }

    /**
     * Método estático para obtener el controlador principal.
     * @return el controlador principal.
     */
    public static MainController getControlador() {
        return controlador;
    }

    /**
     * Método estático para obtener el controlador del juego.
     * @return el controlador del juego.
     */
    public static GameController getGameController() {
        return (gameController == null ? gameController = new GameController() : gameController);
    }

    /**
     * Método estático para establecer el controlador del juego.
     * @param gameController el controlador del juego.
     */
    public static void setGameController(GameController gameController) {
        App.gameController = gameController;
    }
}
