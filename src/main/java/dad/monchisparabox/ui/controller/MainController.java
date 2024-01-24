package dad.monchisparabox.ui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	
    @FXML
    private BorderPane view;
    
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
		/*
		 * Aquí se establece lo que queremos mostrar...
		 * getView().setCenter(new MapController().getView());
		 */
	}

	public BorderPane getView() {
		return view;
	}
}
