package dad.monchisparabox;

import dad.monchisparabox.ui.controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App  extends Application {

	//controllers
	private MainController controlador = new MainController();
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		primaryStage.setTitle("Yanet López Rodríguez");
		primaryStage.setScene(new Scene(controlador.getView(), 928, 522));
        primaryStage.initStyle(StageStyle.UNDECORATED);
		//primaryStage.setResizable(false);
		primaryStage.show();
		
	}
}
