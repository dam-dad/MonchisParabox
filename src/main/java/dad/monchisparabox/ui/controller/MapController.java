package dad.monchisparabox.ui.controller;

import dad.auraengine.media.Music;
import dad.monchisparabox.game.Game;
import dad.monchisparabox.game.utilities.Tile;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MapController implements Initializable {

	@FXML
	private BorderPane view;
	private Game game;
	private Music music;

	public MapController() {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MapView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle resourceBundle) {
		music = new Music("Priscila");
		/*	music.play();*/
		Platform.runLater(() -> {
			setGame(new Game(Tile.mapa));
		});
	}

	public BorderPane getView() {
		return view;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		if (getGame() != null)
			getGame().stop();
		this.game = game;
		getGame().start();
		view.setCenter(getGame().getInitialMap());
	}
}
