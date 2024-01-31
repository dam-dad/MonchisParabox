package dad.monchisparabox.game.entities;

import dad.auraengine.entities.Entity;
import dad.auraengine.entities.movements.Direction;
import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Rectangle;

public class Player extends Entity<Rectangle> {

	private ImageView playerComponent;

	private Location location;
	private Direction direction;

	public Player() {
		super();
		// load player image
		image = new Image("/assets/entities/player.png");
		
		playerComponent = new ImageView();
		playerComponent.setImage(image);

		// default direction when starting
		this.direction = Direction.NONE;

		// variables of character size
		this.width = (int) 50;
		this.height = (int) 50;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	
	//Esto habría que verlo dónde se renderiza el mapa y cuidado porque duplicamos al jugador
	//entonces (visualmente) XD
	@Override
	public void render() {
		location.getMap().addEntity(playerComponent, location.getX(), location.getY());
	}

	public void movePlayer() {
		// Aunq esto en realidad es X e Y (del location)
		// esto da asco

		int currentColumn = GridPane.getColumnIndex(playerComponent);
		int currentRow = GridPane.getRowIndex(playerComponent);

		switch (direction) {
		case EAST:
			if (currentColumn < 10 - 1) {
				GridPane.setColumnIndex(playerComponent, currentColumn + 1);
			}
			break;
		case WEST:
			if (currentColumn > 0) {
				GridPane.setColumnIndex(playerComponent, currentColumn - 1);
			}
			break;
		case NORTH:
			if (currentRow > 0) {
				GridPane.setRowIndex(playerComponent, currentRow - 1);
			}
			break;
		case SOUTH:
			if (currentRow < 10 - 1) {
				GridPane.setRowIndex(playerComponent, currentRow + 1);
			}
			break;
		default:
			break;
		}
	}

	@Override
	public Rectangle getCollisionShape() {
		return new Rectangle(width, height);
	}
}