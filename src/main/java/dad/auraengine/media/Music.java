package dad.auraengine.media;

import java.net.URISyntaxException;
import java.net.URL;

import dad.monchisparabox.ui.controller.MainController;
import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Clase que controla la reproducción de música en el juego.
 */
public class Music {
	
	private MediaPlayer player;
	private Media media;
	
	/**
	 * Constructor que recibe el nombre del archivo de música.
	 * @param file el nombre del archivo de música (sin la extensión).
	 */
	public Music(String file) {
		try {
			// Obtener la URL del archivo de música
			URL path = getClass().getResource("/music/" + file + ".mp3");
			if (path == null) {
				throw new IllegalArgumentException("El archivo de música no se encontró: " + file);
			}
			this.media = new Media(path.toURI().toString());
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Inicia la reproducción de la música.
	 */
	public void play() {
		player = new MediaPlayer(media);
		player.setVolume(0.3); // Establecer el volumen
		player.setCycleCount(Transition.INDEFINITE); // Reproducir indefinidamente
		player.play();
	}

	/**
	 * Detiene la reproducción de la música.
	 */
	public void stopMusic() {
		if (player != null) {
			player.stop();
		}
	}

	/**
	 * Inicia la reproducción de la música con el volumen especificado.
	 * @param volume el volumen de reproducción.
	 */
	public void play(double volume) {
		player = new MediaPlayer(media);
		player.setVolume(volume);
		player.setCycleCount(Transition.INDEFINITE);
		player.play();
	}

	/**
	 * Reproduce la música una vez.
	 * Si los efectos de sonido están habilitados, reproduce la música una vez.
	 */
	public void playOnce() {
		if(MainController.getUserData().efectosEnabled()){
			MediaPlayer oneTimePlayer = new MediaPlayer(media);
			oneTimePlayer.setVolume(0.3);
			oneTimePlayer.setOnEndOfMedia(() -> oneTimePlayer.dispose()); // Liberar recursos después de reproducir una vez
			oneTimePlayer.play();
		}
	}
	
	/**
	 * Pausa la reproducción de la música.
	 */
	public void pause() {
		try {
			player.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPlayer(MediaPlayer player) {
		this.player = player;
	}
}
