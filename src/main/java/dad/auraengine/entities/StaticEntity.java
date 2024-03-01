package dad.auraengine.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

/**
 *   Genera un objeto de transicion que visualmente es igual a un tile de suelo
 *   emplea el metodo getShape para comprobar colisiones con el player
 */
public class StaticEntity<T extends Shape> extends Entity<T> {

	private ImageView imageView;

	public StaticEntity(Image image, Location location) {
		super();
		this.image = image;
		this.location = location;
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();
	}

	@Override
	public void render() {
		imageView = new ImageView(image);
		location.getMap().addEntity(imageView, location.getX(), location.getY());
	}

	@Override
	public T getCollisionShape() {
		return null;
	}

	@Override
	public void destroy() {
		if(imageView != null) {
			location.getMap().getChildren().remove(imageView);
		}
	}

}
