package dad.monchisparabox.game.entities;

import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Player extends Entity<Rectangle> {

	private Image playerImage;

	private Location location;
	private Direction direction;

	public Player() {
		super();

		// load player image
		playerImage = new Image("/assets/entities/");

		// default direction when starting
		this.direction = Direction.SOUTH;

		// variables of character size
		this.width = (int) 50;
		this.height = (int) 50;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}

	// methods for movement
	private void moveLeft() {
		direction = Direction.EAST;
		
	}

	private void moveRight() {
		direction = Direction.WEST;
		
	}

	private void moveUp() {
		direction = Direction.NORTH;
		
	}

	private void moveDown() {
		direction = Direction.SOUTH;
		
	}
	
	@Override
	public void render() {
		//TODO
	}
	
	@Override
	public Rectangle getCollisionShape() {
		return new Rectangle(width, height);
	}
}