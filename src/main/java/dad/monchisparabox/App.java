package dad.monchisparabox;

import dad.monchisparabox.game.controller.GameController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static GameController gameController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		gameController = new GameController();

        primaryStage.setTitle("Monchi's Parabox");
        primaryStage.setScene(new Scene(gameController.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();
	}

  public static GameController getGameController() {
      return gameController;
  }
}
