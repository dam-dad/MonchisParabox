package dad.auraengine.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

/**
 *   Genera un objeto de transicion que visualmente es igual a un tile de suelo
 *   emplea el metodo getShape para comprobar colisiones con el player
 */
public class CollidableEntity extends StaticEntity<Rectangle> {
	
	public CollidableEntity(Image image, Location location) {
		super(image, location);
	}

	@Override
	public Rectangle getCollisionShape() {
		return new Rectangle(width, height);
	}
}
