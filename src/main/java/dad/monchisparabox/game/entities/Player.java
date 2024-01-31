package dad.monchisparabox.game.entities;

import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
}