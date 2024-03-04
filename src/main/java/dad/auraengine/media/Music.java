package dad.auraengine.media;

import java.net.URISyntaxException;
import java.net.URL;

import javafx.animation.Transition;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Clase que controla la musica
 *
 */
public class Music {
	
	private MediaPlayer player;
	private Media media;
	
	/**
	 * Constructor que recibe el nombre del archivo de musica
	 * @param el fichero de musica
	 */
	public Music(String file) {
		try {
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
	 * Método para iniciar musica
	 */
	public void play() {
		player = new MediaPlayer(media);
		player.setVolume(0.3);
		player.setCycleCount(Transition.INDEFINITE);
		player.play();
	}


	public void playOnce() {
		MediaPlayer oneTimePlayer = new MediaPlayer(media);
		oneTimePlayer.setVolume(0.3);
		oneTimePlayer.setOnEndOfMedia(() -> oneTimePlayer.dispose()); // Liberar recursos después de reproducir una vez
		oneTimePlayer.play();
	}
	
	/**
	 * Método para pausar la música
	 */
	public void pause() {
		try {
			player.stop();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
