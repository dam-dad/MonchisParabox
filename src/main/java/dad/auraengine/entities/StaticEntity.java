package dad.auraengine.entities;

import dad.auraengine.Map;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;

/**
 *   Genera un objeto de transicion que visualmente es igual a un tile de suelo
 *   emplea el metodo getShape para comprobar colisiones con el player
 */
public class StaticEntity<T extends Shape> extends Entity<T> {
	
	public StaticEntity(Image image, int x, int y) {
		super();
		this.image = image;
		this.posX = x;
		this.posY = y;
		this.width = (int) image.getWidth();
		this.height = (int) image.getHeight();
	}

	@Override
	public void render(Map map) {
		map.addEntity(new ImageView(image), posX, posY);
	}

	@Override
	public T getCollisionShape() {
		return null;
	}

}
