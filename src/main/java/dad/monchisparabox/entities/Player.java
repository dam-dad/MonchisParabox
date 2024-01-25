package dad.monchisparabox.entities;

import dad.auraengine.Map;
import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Player extends Entity<Rectangle> {

	private Image playerImage;

	private Direction direction;

	public Player(int posX, int posY, int speed) {
		super();

		// load player image
		playerImage = new Image("/assets/CAMBIARLO");
		
		// variables of starting position
		this.posX = posX;
		this.posY = posY;

		// default direction when starting
		this.direction = Direction.SOUTH;

		// variables of character size
		this.width = (int) 50;
		this.height = (int) 50;
	}

	// methods for movement
	private void moveLeft() {
		direction = Direction.EAST;
		
		//Change gridpane 
		
		System.out.println("Y: " + posY + " X: " + posX);
	}

	private void moveRight() {
		direction = Direction.WEST;
		
		System.out.println("Y: " + posY + " X: " + posX);
	}

	private void moveUp() {
		direction = Direction.NORTH;
		
		System.out.println("Y: " + posY + " X: " + posX);
	}

	private void moveDown() {
		direction = Direction.SOUTH;
		
		System.out.println("Y: " + posY + " X: " + posX);
	}
	
	public void render(Map map) {
		
	}
	
	@Override
	public Rectangle getCollisionShape() {
		return new Rectangle(width, height);
	}

}