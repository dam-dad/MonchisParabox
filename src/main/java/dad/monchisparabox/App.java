package dad.monchisparabox;

import dad.monchisparabox.game.controller.GameController;
import dad.monchisparabox.ui.controller.MainController;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App  extends Application {

	private static Stage primaryStage;

	//controllers
	private static MainController controlador = new MainController();
	private static GameController gameController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		App.primaryStage = primaryStage;

		primaryStage.setTitle("Monchi's Parabox");
		primaryStage.setScene(new Scene(controlador.getView(), 928, 522));
		primaryStage.setMaximized(true);
		primaryStage.show();
	}

	public static MainController getControlador() {
		return controlador;
	}

	public static GameController getGameController() {
		return (gameController == null ? gameController = new GameController() : gameController);
	}
}
