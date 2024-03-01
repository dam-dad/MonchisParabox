package dad.monchisparabox.game.entities;

import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import dad.monchisparabox.game.block.Block;
import dad.monchisparabox.game.block.BoxBlock;
import dad.monchisparabox.game.block.LimitBlock;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Player extends Entity<Rectangle> {

	private ImageView playerComponent;

	private Location location;

	public Player() {
		super();
		// load player image
		image = new Image("/assets/entities/player.png");

		playerComponent = new ImageView();
		playerComponent.setImage(image);

		// variables of character size
		this.width = (int) 50;
		this.height = (int) 50;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public Location getLocation() {
		return location;
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

		switch (direction) {
			case LEFT:
				location.decrementX();
				break;
			case RIGHT:
				location.incrementX();
				break;
			case UP:
				location.decrementY();
				break;
			case DOWN:
				location.incrementY();
				break;
			default:
				break;
		}

		Block block = location.getMap().getBlockAt(location, null);
		if(block != null) {
			block.handleCollision(this, direction);
		}
	}

	public void cancelMove() {
		location.setX(location.getLastX());
		location.setY(location.getLastY());
	}
}