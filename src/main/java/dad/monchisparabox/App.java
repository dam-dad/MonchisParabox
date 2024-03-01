package dad.monchisparabox;

import dad.auraengine.media.Music;
import dad.monchisparabox.ui.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class App extends Application {

    private static MainController mainController;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		mainController = new MainController();

        primaryStage.setTitle("Monchi's Parabox");
        primaryStage.setScene(new Scene(mainController.getView()));
        primaryStage.setMaximized(true);
        primaryStage.show();



    }
}
