package dad.auraengine.entities;

import dad.auraengine.Map;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * Clase que crea una entidad a partir de la cuál heredaremos para crear jugadores, npc o tiles dentro del juego.
 * Tiene propiedades que tendrán en común estos objetos como tamaño, imagen y los métodos como mostrarlos en el juego y actualizarlos.
 */
public abstract class Entity<T extends Shape> {
	
	public static final double SCALE = 0.43;
	
	protected int posX, posY;
	protected int width, height;
	protected Image image;
	
	public abstract void render(Map map);
	public abstract T getCollisionShape();
	
	/**
	 * comprobar las colisiones usando la interseccion
	 * @param entidad para comprobar colision
	 * @return
	 */
	public boolean checkCollision(Entity<? extends Shape> entity) {
		return (getCollisionShape() != null && entity.getCollisionShape() != null && getCollisionShape().intersects(entity.getCollisionShape().getLayoutBounds()));
	}
	
	public Image getImage() {
		return image;
	}

}
