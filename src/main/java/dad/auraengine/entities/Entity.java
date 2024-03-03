package dad.auraengine.entities;

import dad.auraengine.entities.movements.Location;
import javafx.scene.image.Image;
import javafx.scene.shape.Shape;

/**
 * Clase que crea una entidad a partir de la cuál heredaremos para crear jugadores, npc o tiles dentro del juego.
 * Tiene propiedades que tendrán en común estos objetos como tamaño, imagen y los métodos como mostrarlos en el juego y actualizarlos.
 */
public abstract class Entity<T extends Shape> {
	
	public static final double SCALE = 0.43;
	
	protected Location location;
	protected int width, height;
	protected Image image;
	
	public abstract void render();
	public abstract T getCollisionShape();
	public abstract void destroy();
	
	/**
	 * comprobar las colisiones usando la interseccion
	 * @param entidad para comprobar colision
	 * @return
	 */
	public boolean checkCollision(Location locChecked) {
        return getLocation().getX() == locChecked.getX() && getLocation().getY() == locChecked.getY();
    }
	
	public Image getImage() {
		return image;
	}
	
	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public void cancelMove() {
		location.setX(location.getLastX());
		location.setY(location.getLastY());
	}
}
