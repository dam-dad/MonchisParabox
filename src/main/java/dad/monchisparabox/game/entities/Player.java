package dad.monchisparabox.game.entities;

import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.App;
import dad.auraengine.media.Music;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.LimitBlock;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;
import javafx.animation.ScaleTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class Player extends Entity<Rectangle> {

	private ImageView playerComponent;
	private ScaleTransition scaleTransition;

	private Music music;

	public Player() {
		super();
		// load player image
		image = new Image("/assets/entities/show_player.png");

		playerComponent = new ImageView();
		playerComponent.setImage(image);

		// variables of character size
		this.width = 50;
		this.height = 50;

		scaleTransition = new ScaleTransition(Duration.seconds(0.2), playerComponent);
		scaleTransition.setToX(0.9); // Escalar en el eje X a 0.9 (encoger un poco)
		scaleTransition.setToY(0.9); // Escalar en el eje Y a 0.9 (encoger un poco)
		scaleTransition.setAutoReverse(true);
	}

	// First time in map
	@Override
	public void render() {
		location.getMap().addEntity(playerComponent, location.getX(), location.getY());
	}

	public void updatePlayer() {
		GridPane.setColumnIndex(playerComponent, location.getX());
		GridPane.setRowIndex(playerComponent, location.getY());
	}

	@Override
	public Rectangle getCollisionShape() {
		return new Rectangle(width, height);
	}

	@Override
	public void destroy() {
		location.getMap().getChildren().remove(playerComponent);
	}

	public void handleMovement(Direction direction) {
		location.setLastX(location.getX());
		location.setLastY(location.getY());
		music = new Music("MovePlayer");

		boolean move = false;

		switch (direction) {
			case LEFT:
				move = location.decrementX();
				music.playOnce();
				break;
			case RIGHT:
				move = location.incrementX();
				music.playOnce();
				break;
			case UP:
				move = location.decrementY();
				music.playOnce();
				break;
			case DOWN:
				move = location.incrementY();
				music.playOnce();
				break;
			default:
				break;
		}

		if(move) {
			Block block = location.getMap().getBlockAt(location, null);
			if (block != null) {
				block.handleCollision(this, direction);
			}
			App.getMainController().getGame().checkWin();
		} else {
			//TODO Volver al mapa anterior
			App.getMainController().getGame().changeMap(location.getMap().getJoinLocation().getMap(), location.getMap().getJoinLocation(), false);
			System.out.println("(Player) Esta saliendo del mapa mi rey");
		}
	}

	public void cancelMove() {
		location.setX(location.getLastX());
		location.setY(location.getLastY());
	}
}